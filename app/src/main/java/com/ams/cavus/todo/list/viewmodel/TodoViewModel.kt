package com.ams.cavus.todo.list.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.ams.cavus.todo.helper.QueryBuilder
import com.ams.cavus.todo.list.adapter.TodoListAdapter
import com.ams.cavus.todo.list.model.Todo
import com.ams.cavus.todo.list.service.TodoService
import com.ams.cavus.todo.login.service.AzureAuthService
import com.example.amstodo.util.SingleLiveEvent
import javax.inject.Inject

class TodoViewModel (app: Application) : AndroidViewModel(app), LifecycleObserver{

    val adapter = TodoListAdapter()

    @Inject
    lateinit var authService: AzureAuthService

    @Inject
    lateinit var todoService: TodoService

    val backToLoginEvent = SingleLiveEvent<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        todoService.fetch(QueryBuilder.buildFetch(authService.currentCredentials.userId)) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

    fun onAddItem() {
        adapter.items.add(Todo(authService.currentCredentials.userId, "Todo"))
        adapter.notifyDataSetChanged()
    }

    fun onSignOut() {
        authService.reset()
        backToLoginEvent.call()
    }

    fun onBackPressed() {
        todoService.pushCache {
            print("Sync completed")
        }
    }

}