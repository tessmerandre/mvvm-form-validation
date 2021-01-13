package com.tessmerandre.app.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class FieldValidation {
    object Valid: FieldValidation()

    class Invalid private constructor(
        private val errorResource: Int? = null,
        private val errorMessage: String? = null
    ): FieldValidation() {

        constructor(message: String): this(null, message)
        constructor(@StringRes message: Int): this(message, null)

        fun getError(context: Context): String {
            return if (errorResource != null) {
                context.getString(errorResource)
            } else {
                errorMessage.orEmpty()
            }
        }
    }
}