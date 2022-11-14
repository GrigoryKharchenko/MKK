package com.mkk.ru.presentation.screen.plusproduct

import androidx.annotation.StringRes
import com.mkk.ru.R

enum class TypeUnits(
    @StringRes val unitsResId: Int,
    @StringRes val priceResId: Int,
    @StringRes val amountResId: Int,
) {
    LITERS(
        unitsResId = R.string.add_product_units_liter,
        priceResId = R.string.add_product_price_liter,
        amountResId = R.string.add_product_amount_liter
    ),
    PIECE(
        unitsResId = R.string.add_product_units_piece,
        priceResId = R.string.add_product_price_piece,
        amountResId = R.string.add_product_amount_piece
    ),
    KILOGRAM(
        unitsResId = R.string.add_product_units_kg,
        priceResId = R.string.add_product_price_kg,
        amountResId = R.string.add_product_amount_kg
    ),
    UNDEFINE(
        unitsResId = R.string.add_product_undefine_units,
        priceResId = R.string.add_product_undefine_price,
        amountResId = R.string.add_product_undefine_amount
    );

    companion object {
        val UNITS = values().toList()
    }
}
