package com.manaois.spot1fy.common

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.manaois.spot1fy.R

abstract class APIErrorDialogUtils {
    companion object {
        fun showErrorDialog(context: Context, view: View, action: (view: View) -> Unit) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.error_header))
            builder.setMessage(context.getString(R.string.error_message))
            builder.setPositiveButton(context.getString(R.string.retry)) { _, _ ->
                action(view)
            }
            builder.setNegativeButton(context.getString(R.string.cancel)) { _, _ -> }
            builder.setCancelable(false)
            builder.show()
        }

        fun showErrorDialog(
            context: Context,
            view: View,
            strInput: String,
            action: (view: View, input: String) -> Unit
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.error_header))
            builder.setMessage(context.getString(R.string.error_message))
            builder.setPositiveButton(context.getString(R.string.retry)) { _, _ ->
                action(view, strInput)
            }
            builder.setNegativeButton(context.getString(R.string.cancel)) { _, _ -> }
            builder.setCancelable(false)
            builder.show()
        }
    }
}