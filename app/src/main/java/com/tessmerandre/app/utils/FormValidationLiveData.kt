package com.tessmerandre.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class FormValidationLiveData(
    vararg fields: LiveData<FieldValidation>
) : MediatorLiveData<Boolean>() {

    init {
        for (field in fields) {
            addSource(field) {
                value = it is FieldValidation.Valid
            }
        }
    }

}