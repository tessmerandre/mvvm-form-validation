package com.tessmerandre.app.utils

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("isValid")
fun isValid(view: View, isValid: LiveData<Int?>) {
    val resId = isValid.value
    when (view) {
        is EditText -> {
            val error = if (resId == null) null else view.context.getString(resId)
            view.error = error
        }
        else -> {
            // Handle all other types of views (spinner...)
        }
    }
}