package com.mkk.ru.presentation.screen.password

import androidx.annotation.ColorRes
import com.mkk.ru.R

enum class DotEnable(@ColorRes val tint: Int) {
    CHECKED(R.color.gray),
    SELECTED(R.color.teal_basic),
    ERROR(R.color.red),
}
