package com.mkk.ru.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(@ColorRes intRes: Int) = ContextCompat.getColor(this, intRes)
