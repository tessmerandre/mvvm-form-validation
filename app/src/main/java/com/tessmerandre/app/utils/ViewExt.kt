package com.tessmerandre.app.utils

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

@BindingAdapter("isValid")
fun isValid(view: View, isValid: MutableLiveData<Int?>) {
    val resId = isValid.value
    val error = if (resId == null) null else view.context.getString(resId)

    when (view) {
        is EditText -> {
            view.error = error
        }
        else -> {
            // Handle all other types of views (spinner...)
        }
    }
}