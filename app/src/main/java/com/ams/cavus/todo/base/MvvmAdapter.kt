package com.ams.cavus.todo.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ams.cavus.todo.R

abstract class MvvmAdapter<TData, TViewDataBinding : ViewDataBinding>: RecyclerView.Adapter<MvvmAdapter.ItemHolder<TViewDataBinding>>() {

    private lateinit var viewDataBinding: TViewDataBinding

    var items = mutableListOf<TData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<TViewDataBinding> {
        viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                onGetLayoutRes(), parent, false)
        return ItemHolder(viewDataBinding)
    }

    override fun getItemCount() = items.size

    abstract fun onGetLayoutRes(): Int;

    class ItemHolder<out TViewDataBinding : ViewDataBinding>(val listItemBinding: TViewDataBinding) : RecyclerView.ViewHolder(listItemBinding.root)
}