package com.mkk.ru.presentation.screen.checks.viewpager.currentshift

import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mkk.ru.R
import com.mkk.ru.databinding.ItemChecksBinding
import com.mkk.ru.domain.model.CheckModel
import com.mkk.ru.extension.inflate

class CurrentChecksAdapter :
    ListAdapter<CheckModel, CurrentChecksViewHolder>(CurrentChecksDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentChecksViewHolder =
        CurrentChecksViewHolder(ItemChecksBinding.bind(parent.inflate(R.layout.item_checks)))

    override fun onBindViewHolder(holder: CurrentChecksViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class CurrentChecksViewHolder(private val binding: ItemChecksBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(checkModel: CheckModel) {
        with(binding) {
            tvDate.text = checkModel.date
            tvTime.text = checkModel.time
            tvNumberCheck.text = checkModel.numberChecks
            tvPrice.text = checkModel.price
            ivError.isInvisible = checkModel.isError
            ivPrinterOff.isInvisible = checkModel.isPrinterOff
        }
    }
}

class CurrentChecksDiffUtil : DiffUtil.ItemCallback<CheckModel>() {
    override fun areItemsTheSame(oldItem: CheckModel, newItem: CheckModel): Boolean =
        oldItem.numberChecks == newItem.numberChecks

    override fun areContentsTheSame(oldItem: CheckModel, newItem: CheckModel): Boolean =
        oldItem == newItem
}
