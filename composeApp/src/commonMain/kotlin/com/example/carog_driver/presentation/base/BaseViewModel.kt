package com.example.carog_driver.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


abstract class BaseViewModel<State, Effect>(
    initialValue: State
): ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialValue)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()


    protected fun sendEffect(effect: Effect){
        viewModelScope.launch(Dispatchers.Main) {
            _effect.emit(effect)
        }
    }

    protected fun updateState(reducer: State.() -> State){
        _state.update { it.reducer() }
    }

    protected fun <R> tryToExecute(
        block: suspend () -> R,
        onSuccess: (R) -> Unit = {},
        onError: (ErrorState) -> Unit = {},
        onStart: () -> Unit = {},
        onEnd: () -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        scope: CoroutineScope = viewModelScope
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            // Call onError
        }

        return scope.launch(dispatcher + exceptionHandler) {
            onStart()

            runCatching {
                block()
            }.onSuccess { result ->
                onSuccess(result)
            }.onFailure { throwable ->
                if (throwable !is CancellationException) {
                    // Map to error state
                }
            }

            onEnd()
        }
    }

    protected fun <R> tryToCollectFlow(
        block: () -> Flow<R>,
        onStart: () -> Unit = {},
        onNewValue: (R) -> Unit,
        onError: (ErrorState) -> Unit = {},
        onEnd: () -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        scope: CoroutineScope = viewModelScope
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            // Call onError
        }

        return scope.launch(dispatcher + exceptionHandler) {
            onStart()

            block()
                .catch { throwable ->
                    if (throwable !is CancellationException) {
                        // Map to error state
                    }
                }
                .collect { value ->
                    onNewValue(value)
                }

            onEnd()
        }
    }
}