package com.ams.cavus.todo.base.di

import com.ams.cavus.todo.base.App
import com.ams.cavus.todo.list.CategoryActivity
import com.ams.cavus.todo.list.GenericItemsActivity
import com.ams.cavus.todo.list.ProductActivity
import com.ams.cavus.todo.list.TodoActivity
import com.ams.cavus.todo.list.di.CategoryModule
import com.ams.cavus.todo.list.di.GenericItemsModule
import com.ams.cavus.todo.list.di.ProductModule
import com.ams.cavus.todo.list.di.TodoModule
import com.ams.cavus.todo.list.viewmodel.CategoryViewModel
import com.ams.cavus.todo.list.viewmodel.GenericItemsViewModel
import com.ams.cavus.todo.list.viewmodel.ProductViewModel
import com.ams.cavus.todo.list.viewmodel.TodoViewModel
import com.ams.cavus.todo.login.LoginActivity
import com.ams.cavus.todo.login.di.LoginModule
import com.ams.cavus.todo.login.viewmodel.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        LoginModule::class,
        TodoModule::class,
        CategoryModule::class,
        ProductModule::class))

interface AppComponent {
    fun inject(app: App)
    fun inject(activity: LoginActivity)
    fun inject(activity: CategoryActivity)
    fun inject(activity: TodoActivity)
    fun inject(activity: ProductActivity)
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: CategoryViewModel)
    fun inject(viewModel: TodoViewModel)
    fun inject(viewModel: ProductViewModel)
}