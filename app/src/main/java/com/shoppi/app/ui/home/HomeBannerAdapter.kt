package com.shoppi.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.model.Banner
import com.shoppi.app.databinding.ItemHomeBannerBinding

class HomeBannerAdapter(private val viewModel: HomeViewModel) :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {
    private lateinit var binding: ItemHomeBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner) {
            binding.banner = banner
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(
        oldItem: Banner,
        newItem: Banner
    ): Boolean { // id 같다고 판단하면 다른 것도 다 같은지 판단하는 과정
        return oldItem == newItem
    }
}