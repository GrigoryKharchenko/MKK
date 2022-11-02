package com.mkk.ru.extension

import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(@StringRes value: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), value, duration).show()
}

fun Fragment.showDialog(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes positiveButton: Int,
    onClickPositiveButton: () -> Unit
) =
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButton) { dialog: DialogInterface, _: Int ->
            onClickPositiveButton()
            dialog.dismiss()
        }
        .show()
