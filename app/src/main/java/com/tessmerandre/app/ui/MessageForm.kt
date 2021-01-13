package com.tessmerandre.app.ui

import androidx.lifecycle.MutableLiveData
import com.tessmerandre.app.R
import com.tessmerandre.app.data.Message
import com.tessmerandre.app.utils.FieldValidation
import com.tessmerandre.app.utils.FormValidationLiveData
import com.tessmerandre.app.utils.validateField

class MessageForm {

    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val observation = MutableLiveData<String>()

    val isTitleValid = title.validateField { isTitleValid(it) }
    val isContentValid = content.validateField { isContentValid(it) }
    val isObservationValid = content.validateField { isObservationValid(it) }

    val canProceed = FormValidationLiveData(isTitleValid, isContentValid, isObservationValid)

    private fun isTitleValid(title: String?): FieldValidation {
        return if (title?.length ?: 0 > 6) {
            FieldValidation.Valid
        } else {
            FieldValidation.Invalid(R.string.error_message_title_invalid)
        }
    }

    private fun isContentValid(content: String?) = FieldValidation.Valid

    private fun isObservationValid(observation: String) = FieldValidation.Valid

    fun makeMessage(): Message {
        return Message(
            title = title.value.orEmpty(),
            content = content.value.orEmpty(),
            observation = observation.value.orEmpty()
        )
    }

}