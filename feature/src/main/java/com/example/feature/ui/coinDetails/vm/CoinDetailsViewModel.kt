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