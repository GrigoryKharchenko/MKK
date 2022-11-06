package com.mkk.ru.presentation.customview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.mkk.ru.R
import com.mkk.ru.databinding.MenuButtonBinding

class MenuButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = MenuButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.MenuButton)
        val backgroundColor = styleAttributes.getColor(
            R.styleable.MenuButton_backgroundColor,
            context.getColor(R.color.teal_dark)
        )
        val icon = styleAttributes.getDrawable(R.styleable.MenuButton_icon)
        val text = styleAttributes.getText(R.styleable.MenuButton_android_text)
        styleAttributes.recycle()

        with(binding) {
            root.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            ivIcon.setImageDrawable(icon)
            ivIcon.isVisible = icon != null
            tvDescription.text = text
        }
    }
}
