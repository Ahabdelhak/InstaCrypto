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