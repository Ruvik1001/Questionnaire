package com.ruvik.common

import android.app.AlertDialog
import android.content.Context

object BaseAlert {
    fun showAlert(context: Context, title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showAlertDEBUG(context: Context, message: String) {
        showAlert(context, "DEBUG", message)
    }
}