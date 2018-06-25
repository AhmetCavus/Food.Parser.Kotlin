package com.ams.cavus.todo.list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmActivity
import com.ams.cavus.todo.databinding.ActivityTodoBinding
import com.ams.cavus.todo.list.viewmodel.TodoViewModel
import com.ams.cavus.todo.login.LoginActivity
import com.ams.cavus.todo.util.app
import javax.inject.Inject

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, userName address, and basic
 * profile.
 */
class TodoActivity : MvvmActivity() {

    private lateinit var viewDataBinding: ActivityTodoBinding

    @Inject
    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The layout for this activity is a Data Binding layout so it needs to be inflated using
        viewDataBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_todo)

        app.component.inject(viewModel)

        viewDataBinding.vm = viewModel.apply {
            lifecycleRegistry.addObserver(this)
            backToLoginEvent.observe(this@TodoActivity, Observer(::onBackToLogin))
        }

        viewDataBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackPressed()
    }

    private fun onBackToLogin(e: Unit?) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}