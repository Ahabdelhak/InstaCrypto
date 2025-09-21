/*
 * Copyright 2022 AHMED ABDELHAK
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.feature.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Base class for [ViewModel] instances using MVI pattern.
 *
 * @param Event Represents user-driven actions (UI events).
 * @param State Represents the current UI state.
 * @param Effect Represents one-time effects (navigation, toasts, etc.).
 */
abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    // --- State ---
    private val initialState: State by lazy { createInitialState() }
    protected abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    val currentState: State get() = _uiState.value

    // --- Events ---
    private val _event = MutableSharedFlow<Event>()
    private val event: SharedFlow<Event> = _event.asSharedFlow()

    // --- Effects ---
    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    /**
     * Subscribes to [Event]s and forwards them to [handleEvent].
     */
    private fun subscribeToEvents() {
        viewModelScope.launch {
            event.collect(::handleEvent)
        }
    }

    /**
     * Handle each incoming [Event].
     */
    protected abstract fun handleEvent(event: Event)

    /**
     * Emit a new [Event].
     */
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    /**
     * Update the current [State].
     */
    protected fun setState(reducer: State.() -> State) {
        _uiState.value = currentState.reducer()
    }

    /**
     * Emit a one-time [Effect].
     */
    protected fun setEffect(builder: () -> Effect) {
        viewModelScope.launch { _effect.send(builder()) }
    }
}
