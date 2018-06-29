package com.ams.cavus.todo.list.adapter

import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmAdapter
import com.ams.cavus.todo.databinding.ListitemGenericBinding
import com.ams.cavus.todo.list.model.GenericItem
import com.ams.cavus.todo.list.viewmodel.GenericItemViewModel
import com.ams.cavus.todo.list.viewmodel.ProductViewModel

class ProductItemsAdapter(val mainVm: ProductViewModel): MvvmAdapter<GenericItem, ListitemGenericBinding>() {

    override fun onGetLayoutRes() = R.layout.listitem_generic

    override fun onBindViewHolder(holder: ItemHolder<ListitemGenericBinding>, position: Int) {
        holder.listItemBinding.vm = GenericItemViewModel(items[position])
    }

}