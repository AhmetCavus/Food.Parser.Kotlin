package com.ams.cavus.todo.list.di

import com.ams.cavus.todo.base.App
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.list.service.TodoService
import com.ams.cavus.todo.list.viewmodel.GenericItemsViewModel
import com.ams.cavus.todo.list.viewmodel.TodoViewModel
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GenericItemsModule {

    @Singleton
    @Provides
    fun provideGenericItemsViewModel(app: App) = GenericItemsViewModel(app)

    @Singleton
    @Provides
    fun provideGenericItemsService(client: MobileServiceClient, gson: Gson, settings: Settings) = GenericItemService(client, gson, settings)
}