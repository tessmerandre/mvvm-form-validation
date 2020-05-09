package com.tessmerandre.app.utils

import androidx.lifecycle.MutableLiveData
import com.tessmerandre.app.R

class ValidationLiveData: MutableLiveData<Int?>() {

    fun invalid(error: Int = R.string.error_required_field) {
        value = error
    }

    fun valid() {
        value = null
    }

}