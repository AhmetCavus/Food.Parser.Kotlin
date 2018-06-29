package com.ams.cavus.todo.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmActivity
import com.ams.cavus.todo.databinding.ActivityProductBinding
import com.ams.cavus.todo.list.viewmodel.ProductViewModel
import com.ams.cavus.todo.util.app
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, userName address, and basic
 * profile.
 */
class ProductActivity : MvvmActivity() {

    private lateinit var viewDataBinding: ActivityProductBinding

    @Inject
    lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The layout for this activity is a Data Binding layout so it needs to be inflated using
        viewDataBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_product)

        app.component.inject(this)
        app.component.inject(viewModel)
        client.context = this

        viewDataBinding.vm = viewModel.apply {
            lifecycleRegistry.addObserver(this)
        }

        viewDataBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }

        var divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(divider)
    }

}