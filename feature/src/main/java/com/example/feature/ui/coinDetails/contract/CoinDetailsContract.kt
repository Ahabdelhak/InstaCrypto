package com.example.feature.ui.coinDetails.contract

import com.example.entity.CoinDetailsResponse
import com.example.feature.core.UiEffect
import com.example.feature.core.UiEvent
import com.example.feature.core.UiState

class CoinDetailsContract {

    sealed class Event : UiEvent {
        data class GetCoinLDetails(val id:String) : Event()
    }

    data class State(
        val coinDetailsState: CoinDetailsState,
    ) : UiState

    sealed class CoinDetailsState {
        object Idle : CoinDetailsState()
        object Loading : CoinDetailsState()
        data class Success(val result : CoinDetailsResponse) : CoinDetailsState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

}