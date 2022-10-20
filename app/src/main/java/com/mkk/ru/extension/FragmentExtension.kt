package com.mkk.ru.extension

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(value: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), value, duration).show()
}
