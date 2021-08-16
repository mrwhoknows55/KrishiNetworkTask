package com.mrwhoknows.krishinetworktask.ui.mandi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mrwhoknows.krishinetworktask.data.model.Mandi
import com.mrwhoknows.krishinetworktask.databinding.MandiItemBinding

class MandiAdapter : ListAdapter<Mandi, MandiAdapter.MandiViewHolder>(MandiComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MandiViewHolder {
        val binding = MandiItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MandiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MandiViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null)
            holder.bind(currentItem)
    }

    class MandiViewHolder(private val binding: MandiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mandi: Mandi) {
            binding.apply {
                title.text = mandi.hindiName
                image.load(mandi.image)
            }
        }

    }

    class MandiComparator : DiffUtil.ItemCallback<Mandi>() {
        override fun areItemsTheSame(oldItem: Mandi, newItem: Mandi): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Mandi, newItem: Mandi): Boolean =
            oldItem == newItem
    }


}