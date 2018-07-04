package com.ams.cavus.todo.list.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.ams.cavus.todo.list.adapter.CategoryItemsAdapter
import com.ams.cavus.todo.list.model.ProductCategory
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.list.service.ProductCategoryService
import com.ams.cavus.todo.login.service.AzureAuthService
import com.example.amstodo.util.SingleLiveEvent
import java.util.*
import javax.inject.Inject

class CategoryViewModel (app: Application) : AndroidViewModel(app), LifecycleObserver{

    var categoryStack = Stack<MutableList<ProductCategory>>()

    @Inject
    lateinit var adapter: CategoryItemsAdapter

    @Inject
    lateinit var categoryService: ProductCategoryService

    @Inject
    lateinit var productService: GenericItemService

    @Inject
    lateinit var authService: AzureAuthService

    val backToLoginEvent = SingleLiveEvent<Unit>()
    val showProductsEvent = SingleLiveEvent<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
//        categoryService.test2()
        categoryService.fetch(null) { items ->
            adapter.items = items
                            .filter { item -> item.parentId == 0 }
                            .sortedBy { item -> item.name }
                            .toMutableList()
            categoryStack.add(adapter.items)
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
            productService.filteredItems =
            productService.itemsCache
                    .filter { product -> product.productCategoryId == categoryViewModel.item.productCategoryId }
                    .toMutableList()
            Log.i("mvvm", "Show products: items count" + productService.filteredItems?.count())
            showProductsEvent?.call()
        } else {
            adapter.items = filteredCategories
            categoryStack.addElement(filteredCategories)
            adapter.notifyDataSetChanged()
        }
    }

    fun onBackPressed(): Boolean {
        var isHandled = false
        categoryStack.pop()
        if(categoryStack.size > 0) {
            adapter.items = categoryStack.lastElement()
            adapter.notifyDataSetChanged()
            isHandled = true
        } else {
            authService.reset()
        }
        return isHandled
    }

}