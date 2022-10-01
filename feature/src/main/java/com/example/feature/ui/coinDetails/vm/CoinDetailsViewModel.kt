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

package com.example.feature.ui.coinDetails.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.usecase.CoinDetailsUseCase
import com.example.feature.core.BaseViewModel
import com.example.feature.ui.coinDetails.contract.CoinDetailsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val coinDetailsUseCase: CoinDetailsUseCase,
) : BaseViewModel<CoinDetailsContract.Event, CoinDetailsContract.State, CoinDetailsContract.Effect>() {



    override fun createInitialState(): CoinDetailsContract.State {
        return CoinDetailsContract.State(
            coinDetailsState = CoinDetailsContract.CoinDetailsState.Idle
        )
    }

    override fun handleEvent(event: CoinDetailsContract.Event) {
        when (event) {
            is CoinDetailsContract.Event.GetCoinLDetails -> {
                getCoinDetails(event.id)
            }
        }
    }

    /**
     * Fetch Details
     */

    private  fun getCoinDetails(id:String) {
        viewModelScope.launch {
            coinDetailsUseCase.execute(id)
                .onStart { emit(Resource.Loading) }
                .collect {

                    when (it) {
                        is Resource.Loading -> {
                            // Set State
                            setState { copy(coinDetailsState = CoinDetailsContract.CoinDetailsState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set State
                            setState { copy(coinDetailsState = CoinDetailsContract.CoinDetailsState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set State
                            setState {
                                copy(
                                    coinDetailsState = CoinDetailsContract.CoinDetailsState.Success(
                                        result = it.data
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { CoinDetailsContract.Effect.ShowError(message = it.message) }
                        }
                    }
                }
        }
    }

}