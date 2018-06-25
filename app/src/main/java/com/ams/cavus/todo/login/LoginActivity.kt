package com.ams.cavus.todo.login

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmActivity
import com.ams.cavus.todo.databinding.ActivityLoginBinding
import com.ams.cavus.todo.list.GenericItemsActivity
import com.ams.cavus.todo.list.TodoActivity
import com.ams.cavus.todo.login.viewmodel.LoginViewModel
import com.ams.cavus.todo.util.app
import javax.inject.Inject

/**
 * A login screen that offers login via userName/password.
 */
class LoginActivity : MvvmActivity() {

    private lateinit var viewDataBinding: ActivityLoginBinding

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The layout for this activity is a Data Binding layout so it needs to be inflated using
        viewDataBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_login)

        app.component.inject(viewModel)

        viewDataBinding.vm = viewModel.apply {
            startActivityForResultEvent.observe(this@LoginActivity, Observer(::onStartActivityForResult))
            showNextEvent.observe(this@LoginActivity, Observer(::onStartActivity))
            lifecycleRegistry.addObserver(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LoginViewModel.TWITTER_CALLBACK -> viewModel.handleTwitterResult(requestCode, resultCode, data)
            LoginViewModel.GOOGLE_CALLBACK -> viewModel.handleGoogleResult(requestCode, resultCode, data)
            LoginViewModel.MICROSOFT_CALLBACK -> viewModel.handleMicrosoftResult(requestCode, resultCode, data)
            LoginViewModel.AD_CALLBACK -> viewModel.handleAdResult(requestCode, resultCode, data)
            else -> viewModel.handleResult(requestCode, resultCode, data)
        }
    }

    private fun onStartActivityForResult(intent: Intent?) {
        startActivityForResult(intent, viewModel.currentCallbackId)
    }

    private fun onStartActivity(next: String?) = when(next) {
        "genericItems" -> startActivity(Intent(this, GenericItemsActivity::class.java))
        "todoList" -> startActivity(Intent(this, TodoActivity::class.java))
        else -> {
            TODO("Do nothing")
        }
    }

}