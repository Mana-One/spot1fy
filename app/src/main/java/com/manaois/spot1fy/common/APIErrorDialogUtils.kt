package com.manaois.spot1fy.common

import android.app.AlertDialog
import android.content.Context
import android.view.View

abstract class APIErrorDialogUtils {
    companion object {
        fun showErrorDialog(context: Context, view: View, action: (view: View) -> Unit) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Title")
            builder.setMessage("Message")
            builder.setPositiveButton("Ok") { _, _ ->
                action(view)
            }
            builder.setNegativeButton("Cancel") { _, _ -> }
            builder.setCancelable(false)
            builder.show()
        }
    }
}