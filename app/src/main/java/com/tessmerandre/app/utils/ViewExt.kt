package com.tessmerandre.app.utils

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("isValid")
fun isValid(view: View, isValid: LiveData<FieldValidation>) {
    val value = isValid.value ?: return
    if (value is FieldValidation.Valid) return

    val error = (value as FieldValidation.Invalid).getError(view.context)

    when (view) {
        is EditText -> {
            view.error = error
        }
        else -> {
            // Handle all other types of views (spinner...) and even custom views
        }
    }

//    fun displayError() {
//    }

//    view.setOnFocusChangeListener { view, b ->
//        if (!b) {
//            displayError()
//        }
//    }
}