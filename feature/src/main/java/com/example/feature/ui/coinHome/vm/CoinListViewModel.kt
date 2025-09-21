/*
 * Copyright 2022 AHMED ABDELHAK
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
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
    private val coinsListUseCase: CoinsListUseCase,
) : BaseViewModel<CoinListContract.Event, CoinListContract.State, CoinListContract.Effect>() {

    init {
        setEvent(CoinListContract.Event.GetCoinList)
    }

    override fun createInitialState(): CoinListContract.State =
        CoinListContract.State(coinListState = CoinListContract.CoinListState.Idle)

    override fun handleEvent(event: CoinListContract.Event) {
        when (event) {
            CoinListContract.Event.GetCoinList -> fetchCoinList()
            is CoinListContract.Event.OnCoinItemClicked -> {
                setEffect { CoinListContract.Effect.Navigate(event.id) }
            }
        }
    }

    /**
     * Fetch coin list from use case and update UI state/effects accordingly.
     */
    private fun fetchCoinList() {
        viewModelScope.launch {
            coinsListUseCase.execute(Unit)
                .onStart { emit(Resource.Loading) }
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> setState {
                            copy(coinListState = CoinListContract.CoinListState.Loading)
                        }
                        is Resource.Empty -> setState {
                            copy(coinListState = CoinListContract.CoinListState.Idle)
                        }
                        is Resource.Success -> setState {
                            copy(
                                coinListState = CoinListContract.CoinListState.Success(
                                    result = resource.data
                                )
                            )
                        }
                        is Resource.Error -> setEffect {
                            CoinListContract.Effect.ShowError(message = resource.message)
                        }
                    }
                }
        }
    }
}
