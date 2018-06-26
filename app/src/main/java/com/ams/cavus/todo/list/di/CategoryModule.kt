package com.ams.cavus.todo.list.di

import com.ams.cavus.todo.base.App
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.adapter.CategoryItemsAdapter
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.list.service.ProductCategoryService
import com.ams.cavus.todo.list.service.TodoService
import com.ams.cavus.todo.list.viewmodel.CategoryViewModel
import com.ams.cavus.todo.list.viewmodel.GenericItemsViewModel
import com.ams.cavus.todo.list.viewmodel.TodoViewModel
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CategoryModule {

    @Singleton
    @Provides
    fun provideProductCategoryService(client: MobileServiceClient, gson: Gson, settings: Settings) = ProductCategoryService(client, gson, settings)

    @Singleton
    @Provides
    fun provideCategoryViewModel(app: App) = CategoryViewModel(app)

    @Singleton
    @Provides
    fun provideCategoryItemsAdapter(mainVm: CategoryViewModel) = CategoryItemsAdapter(mainVm)
}