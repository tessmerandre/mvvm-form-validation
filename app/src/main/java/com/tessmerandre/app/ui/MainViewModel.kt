package com.tessmerandre.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tessmerandre.app.R
import com.tessmerandre.app.data.Message
import com.tessmerandre.app.utils.ValidationLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val observation = MutableLiveData<String>()

    val isTitleValid = ValidationLiveData()
    val isContentValid = ValidationLiveData()

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun save() {
        viewModelScope.launch {
            if (!canProceed()) return@launch
            emitUiState(loading = true)
            withContext(Dispatchers.IO) {
                val message = makeMessage()
                // repository.saveMessage(message)
                delay(1000) // faking service call
                emitUiState(loading = false, succeed = true)
            }
        }
    }

    private fun makeMessage(): Message {
        return Message(
            title = title.value.orEmpty(),
            content = content.value.orEmpty(),
            observation = observation.value
        )
    }

    private fun canProceed(): Boolean {
        if (title.value?.length ?: 0 < 6) {
            isTitleValid.invalid(R.string.error_message_title_invalid)
            return false
        } else {
            isTitleValid.valid()
        }

        if (content.value.isNullOrEmpty() || content.value?.isBlank() == true) {
            isContentValid.invalid()
            return false
        } else {
            isContentValid.valid()
        }

        return true
    }

    private suspend fun emitUiState(
        loading: Boolean = false,
        succeed: Boolean = false,
        failed: Boolean = false
    ) {
        withContext(Dispatchers.Main) {
            _uiState.value = UiState(loading, succeed, failed)
        }
    }

}

data class UiState(
    val loading: Boolean = false,
    val succeed: Boolean = false,
    val failed: Boolean = false
)