package com.shoppi.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemCategoryPromotionBinding
import com.shoppi.app.model.Product

class CategoryPromotionAdapter: ListAdapter<Product, CategoryPromotionAdapter.CategoryPromotionViewHolder>(PromotionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPromotionViewHolder {
        val binding = ItemCategoryPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryPromotionViewHolder((binding))
    }

    override fun onBindViewHolder(holder: CategoryPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryPromotionViewHolder(private val binding: ItemCategoryPromotionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(producut: Product) {
            binding.product = producut
            binding.executePendingBindings()
        }
    }
}

class PromotionDiffCallback : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}