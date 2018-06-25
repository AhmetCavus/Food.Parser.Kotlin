package com.ams.cavus.todo.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ams.cavus.todo.R
import com.ams.cavus.todo.databinding.ListitemGenericBinding
import com.ams.cavus.todo.list.model.GenericItem
import com.ams.cavus.todo.list.viewmodel.GenericItemViewModel


class GenericItemsAdapter: RecyclerView.Adapter<GenericItemsAdapter.GenericItemHolder>() {

    lateinit var viewDataBinding: ListitemGenericBinding

    var genericItems = mutableListOf<GenericItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemHolder {
        viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.listitem_generic, parent, false)
        return GenericItemHolder(viewDataBinding)
    }

    override fun getItemCount() = genericItems.size

    override fun onBindViewHolder(holder: GenericItemHolder, position: Int) {
        holder.listItemBinding.vm = GenericItemViewModel(genericItems[position])
    }

    class GenericItemHolder(val listItemBinding: ListitemGenericBinding) : RecyclerView.ViewHolder(listItemBinding.root)

}