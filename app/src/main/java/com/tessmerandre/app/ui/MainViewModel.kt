package com.tessmerandre.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tessmerandre.app.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val form = MessageForm()

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun save() {
        viewModelScope.launch {
            if (form.canProceed.value == false) return@launch

            emitUiState(loading = true)

            withContext(Dispatchers.IO) {
                val message = form.makeMessage()
                delay(1500) // faking service call
            }

            emitUiState(loading = false, succeed = true)
        }
    }

    private fun emitUiState(
        loading: Boolean = false,
        succeed: Boolean = false,
        failed: Boolean = false
    ) {
        _uiState.value = UiState(loading, Event(succeed), Event(failed))
    }

}

data class UiState(
    val loading: Boolean,
    val succeed: Event<Boolean>,
    val failed: Event<Boolean>
)