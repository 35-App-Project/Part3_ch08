package com.choi.part3_ch08.presenter.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.choi.part3_ch08.databinding.ItemContentBinding
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.presenter.ui.MainActivity

class ContentViewHolder(
    private val binding: ItemContentBinding,
    private val handler: MainActivity.Handler
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Content) {
        binding.item=item
        binding.handler=handler
    }
}