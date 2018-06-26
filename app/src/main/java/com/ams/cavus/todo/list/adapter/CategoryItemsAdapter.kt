package com.ams.cavus.todo.list.adapter

import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmAdapter
import com.ams.cavus.todo.databinding.ListitemCategoryBinding
import com.ams.cavus.todo.list.model.ProductCategory
import com.ams.cavus.todo.list.viewmodel.CategoryItemViewModel
import com.ams.cavus.todo.list.viewmodel.CategoryViewModel

class CategoryItemsAdapter(val mainVm: CategoryViewModel): MvvmAdapter<ProductCategory, ListitemCategoryBinding>() {

    override fun onGetLayoutRes() = R.layout.listitem_category

    override fun onBindViewHolder(holder: ItemHolder<ListitemCategoryBinding>, position: Int) {
        holder.listItemBinding.itemViewModel = CategoryItemViewModel(items[position])
        holder.listItemBinding.mainViewModel = mainVm
    }

}