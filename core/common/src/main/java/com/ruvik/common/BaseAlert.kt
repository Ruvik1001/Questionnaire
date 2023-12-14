package com.ruvik.common

import android.app.AlertDialog
import android.content.Context

/**
 * Utility class for creating simple alert dialogs.
 */
object BaseAlert {

    /**
     * Shows a basic alert dialog with the provided title and message.
     *
     * @param context The context in which the dialog should be displayed.
     * @param title The title of the alert dialog.
     * @param message The message to be displayed in the alert dialog.
     */
    fun showAlert(context: Context, title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    /**
     * Shows a basic alert dialog with a "DEBUG" title and the provided message.
     * This is intended for debugging purposes.
     *
     * @param context The context in which the dialog should be displayed.
     * @param message The message to be displayed in the alert dialog.
     */
    fun showAlertDEBUG(context: Context, message: String) {
        showAlert(context, "DEBUG", message)
    }
}
