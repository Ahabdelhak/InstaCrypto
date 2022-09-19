package com.example.feature.ui.coinHome.contract


import com.example.entity.CoinListResponse
import com.example.feature.core.UiEffect
import com.example.feature.core.UiEvent
import com.example.feature.core.UiState

/**
 * Contract of Main Screen
 */
class CoinListContract {

    sealed class Event : UiEvent {
        object GetCoinList : Event()
        data class OnCoinItemClicked(val id : String) : Event()
    }

    data class State(
        val coinListState: CoinListState
    ) : UiState

    sealed class CoinListState {
        object Idle : CoinListState()
        object Loading : CoinListState()
        data class Success(val result : CoinListResponse) : CoinListState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
        data class Navigate(val id : String) : Effect()
    }

}