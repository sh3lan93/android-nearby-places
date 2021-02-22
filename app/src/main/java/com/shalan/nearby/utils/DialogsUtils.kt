package com.shalan.nearby.utils

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shalan.nearby.R

/**
 * Created by Mohamed Shalan on 2/20/21
 */

object DialogsUtils {

    inline fun showRationalDialog(
        context: Context,
        @StringRes message: Int,
        crossinline positiveAction: () -> Unit,
        crossinline negativeAction: () -> Unit
    ) {
        MaterialAlertDialogBuilder(context).apply {
            setMessage(message)
            setCancelable(false)
            setPositiveButton(context.getString(R.string.ok), { dialogInterface, which ->
                positiveAction.invoke()
                dialogInterface.dismiss()
            })
            setNegativeButton(
                context.getString(R.string.deny_permission),
                { dialogInterface, which ->
                    negativeAction.invoke()
                    dialogInterface.dismiss()
                })
        }.also {
            it.show()
        }
    }
}