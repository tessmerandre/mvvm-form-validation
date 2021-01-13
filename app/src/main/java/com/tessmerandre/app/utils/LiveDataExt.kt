package com.tessmerandre.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> LiveData<T>.validateField(validate: (T) -> FieldValidation): LiveData<FieldValidation> {
    val result = MediatorLiveData<FieldValidation>()
    result.addSource(this) {
        result.value = validate(it)
    }
    return result
}