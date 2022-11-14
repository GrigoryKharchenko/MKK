package com.mkk.ru.presentation.customview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.mkk.ru.R
import com.mkk.ru.databinding.PaymentButtonBinding
import com.mkk.ru.extension.getCompatColor

class PaymentButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = PaymentButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.PaymentButton)
        val backgroundColor = styleAttributes.getColor(
            R.styleable.PaymentButton_backgroundBloom,
            context.getCompatColor(R.color.light_gray)
        )
        val textAmount = styleAttributes.getText(R.styleable.PaymentButton_textAmount)
        val textDescription = styleAttributes.getText(R.styleable.PaymentButton_textDescription)
        val icon = styleAttributes.getDrawable(R.styleable.PaymentButton_iconCurrency)
        styleAttributes.recycle()

        with(binding) {
            root.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            ivCurrency.setImageDrawable(icon)
            ivCurrency.isVisible = icon != null
            tvDescription.text = textDescription
            tvAmount.text = textAmount
        }
    }

    fun setAmount(amount: String) {
        binding.tvAmount.text = amount
    }
}
