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