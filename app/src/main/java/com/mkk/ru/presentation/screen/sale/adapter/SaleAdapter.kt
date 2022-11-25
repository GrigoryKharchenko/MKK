package com.mkk.ru.presentation.screen.sale.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mkk.ru.R
import com.mkk.ru.databinding.ItemProductBinding
import com.mkk.ru.presentation.screen.sale.ProductUiModel

class SaleAdapter :
    ListAdapter<ProductUiModel, SaleViewHolder>(SaleDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder =
        SaleViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class SaleViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(productUiModel: ProductUiModel) {
        with(binding) {
            tvProduct.text = productUiModel.product
            tvProductCode.text = itemView.context.getString(R.string.sale_item_product_code, productUiModel.productCode)
            tvAmount.text = productUiModel.amount
            tvPrice.text = itemView.context.getString(R.string.sale_item_price, productUiModel.price)
            tvGeneralPrice.text = productUiModel.generalPrice
        }
    }
}

class SaleDiffUtil : DiffUtil.ItemCallback<ProductUiModel>() {
    override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem.productCode == newItem.productCode

    override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean =
        oldItem == newItem
}
