package com.ams.cavus.todo.list.di

import com.ams.cavus.todo.base.App
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.adapter.ProductItemsAdapter
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.list.viewmodel.ProductViewModel
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductModule {

    @Singleton
    @Provides
    fun provideProductItemService(client: MobileServiceClient, gson: Gson, settings: Settings) = GenericItemService(client, gson, settings)

    @Singleton
    @Provides
    fun provideProductViewModel(app: App) = ProductViewModel(app)

    @Singleton
    @Provides
    fun provideProductItemsAdapter(mainVm: ProductViewModel) = ProductItemsAdapter(mainVm)
}