package com.mkk.ru.presentation.customview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.mkk.ru.R
import com.mkk.ru.databinding.ButtonWithTopIconBinding

class ButtonWithTopIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ButtonWithTopIconBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.ButtonWithTopIcon)
        val backgroundColor = styleAttributes.getColor(
            R.styleable.ButtonWithTopIcon_backgroundColor,
            context.getColor(R.color.teal_dark)
        )
        val icon = styleAttributes.getDrawable(R.styleable.ButtonWithTopIcon_icon)
        val text = styleAttributes.getText(R.styleable.ButtonWithTopIcon_android_text)
        styleAttributes.recycle()

        with(binding) {
            root.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            ivIcon.setImageDrawable(icon)
            ivIcon.isVisible = icon != null
            tvDescription.text = text
        }
    }
}
