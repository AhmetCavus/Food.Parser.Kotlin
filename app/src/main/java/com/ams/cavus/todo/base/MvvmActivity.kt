package com.ams.cavus.todo.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ams.cavus.todo.util.app
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import javax.inject.Inject

abstract class MvvmActivity : AppCompatActivity(), LifecycleOwner {

    protected val lifecycleRegistry = LifecycleRegistry(this)

    protected lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var client: MobileServiceClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        linearLayoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

}