package com.ruvik.common

import android.content.Context
import android.widget.Toast

/**
 * Utility class for showing long-duration toasts.
 */
object LongToast {

    /**
     * Displays a long-duration toast message.
     *
     * @param context The context in which the toast should be displayed.
     * @param text The text to be shown in the toast.
     */
    fun makeToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}