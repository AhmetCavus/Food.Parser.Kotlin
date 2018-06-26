package com.ams.cavus.todo.list.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.ams.cavus.todo.base.MvvmAdapter
import com.ams.cavus.todo.list.adapter.CategoryItemsAdapter
import com.ams.cavus.todo.list.adapter.GenericItemsAdapter
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.list.service.ProductCategoryService
import com.example.amstodo.util.SingleLiveEvent
import javax.inject.Inject

class CategoryViewModel (app: Application) : AndroidViewModel(app), LifecycleObserver{

    @Inject
    lateinit var adapter: CategoryItemsAdapter

    @Inject
    lateinit var categoryService: ProductCategoryService

    @Inject
    lateinit var productService: GenericItemService

    val backToLoginEvent = SingleLiveEvent<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        categoryService.fetch(null) { items ->
            adapter.items = items
                            .filter { item -> item.parentId == 0 }
                            .sortedBy { item -> item.name }
                            .toMutableList()
            adapter.notifyDataSetChanged()
            productService.fetch(null) {}
        }
    }

    fun onItemClick(categoryViewModel: CategoryItemViewModel) {
        Log.i("mvvm", categoryViewModel.name)
        val filteredCategories =
                categoryService.itemsCache
                        .filter { category -> category.parentId == categoryViewModel.item.productCategoryId }
                        .toMutableList()
        if(filteredCategories.count() <= 0) {
            var filteredProducts =
            productService.itemsCache
                    .filter { product -> product.productCategoryId == categoryViewModel.item.productCategoryId }
                    .toMutableList()
            Log.i("mvvm", "Show products: items count" + filteredProducts?.count())
        } else {
            adapter.items = filteredCategories;
        }
        adapter.notifyDataSetChanged()
    }

}