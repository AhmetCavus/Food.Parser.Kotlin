package com.ams.cavus.todo.list.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.ams.cavus.todo.list.adapter.GenericItemsAdapter
import com.ams.cavus.todo.list.service.GenericItemService
import com.ams.cavus.todo.login.service.AzureAuthService
import com.example.amstodo.util.SingleLiveEvent
import javax.inject.Inject

class GenericItemsViewModel (app: Application) : AndroidViewModel(app), LifecycleObserver{

    val adapter = GenericItemsAdapter()

    @Inject
    lateinit var authService: AzureAuthService

    @Inject
    lateinit var genericItemsService: GenericItemService

    val backToLoginEvent = SingleLiveEvent<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        genericItemsService.fetch(null) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

    fun onSignOut() {
        authService.reset()
        backToLoginEvent.call()
    }

}