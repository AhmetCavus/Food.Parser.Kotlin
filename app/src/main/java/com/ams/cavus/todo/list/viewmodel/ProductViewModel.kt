package com.ams.cavus.todo.list.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.ams.cavus.todo.list.adapter.ProductItemsAdapter
import com.ams.cavus.todo.list.service.GenericItemService
import javax.inject.Inject

class ProductViewModel (app: Application) : AndroidViewModel(app), LifecycleObserver{

    @Inject
    lateinit var adapter: ProductItemsAdapter

    @Inject
    lateinit var productService: GenericItemService

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        adapter.items = productService.filteredItems
        adapter.notifyDataSetChanged()
    }

    fun onItemClick(productViewModel: ProductItemViewModel) {
        Log.i("mvvm", productViewModel.name)
    }

}