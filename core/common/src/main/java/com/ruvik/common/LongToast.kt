package com.ruvik.common

import android.content.Context
import android.widget.Toast

object LongToast {
    fun makeToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}