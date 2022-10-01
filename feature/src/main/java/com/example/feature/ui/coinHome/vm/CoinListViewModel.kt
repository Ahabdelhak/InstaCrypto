/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.feature.ui.coinHome.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.usecase.CoinsListUseCase
import com.example.feature.core.BaseViewModel
import com.example.feature.ui.coinHome.contract.CoinListContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCoinUseCase: CoinsListUseCase,
) : BaseViewModel<CoinListContract.Event, CoinListContract.State, CoinListContract.Effect>() {


    init {
        setEvent(CoinListContract.Event.GetCoinList)
    }

    override fun createInitialState(): CoinListContract.State {
        return CoinListContract.State(
            coinListState = CoinListContract.CoinListState.Idle
        )
    }

    override fun handleEvent(event: CoinListContract.Event) {
        when (event) {
            is CoinListContract.Event.GetCoinList -> {
                getCoinList()
            }
            is CoinListContract.Event.OnCoinItemClicked -> {
                val id = event.id
                setEffect { CoinListContract.Effect.Navigate(id) }
            }
        }
    }

    /**
     * Fetch List
     */
    private fun getCoinList() {
        viewModelScope.launch {
            getCoinUseCase.execute(Any())
                .onStart { emit(Resource.Loading) }
                .collect {

                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(coinListState = CoinListContract.CoinListState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(coinListState = CoinListContract.CoinListState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            setState {
                                copy(
                                    coinListState = CoinListContract.CoinListState.Success(
                                        result = it.data
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { CoinListContract.Effect.ShowError(message = it.message) }
                        }
                    }
                }
        }
    }

}