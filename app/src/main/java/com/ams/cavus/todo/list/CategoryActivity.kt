package com.ams.cavus.todo.list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmActivity
import com.ams.cavus.todo.databinding.ActivityCategoryBinding
import com.ams.cavus.todo.list.viewmodel.CategoryViewModel
import com.ams.cavus.todo.login.LoginActivity
import com.ams.cavus.todo.util.app
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, userName address, and basic
 * profile.
 */
class CategoryActivity : MvvmActivity() {

    private lateinit var viewDataBinding: ActivityCategoryBinding

    @Inject
    lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The layout for this activity is a Data Binding layout so it needs to be inflated using
        viewDataBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_category)

        app.component.inject(this)
        app.component.inject(viewModel)
        client.context = this

        viewDataBinding.vm = viewModel.apply {
            lifecycleRegistry.addObserver(this)
            backToLoginEvent.observe(this@CategoryActivity, Observer(::onBackToLogin))
            showProductsEvent.observe(this@CategoryActivity, Observer(::onShowProducts))
        }

        viewDataBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }

        var divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

    private fun onBackToLogin(e: Unit?) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun onShowProducts(e: Unit?) {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if(viewModel.onBackPressed()) return
        else {
            super.onBackPressed()
        }
    }
}