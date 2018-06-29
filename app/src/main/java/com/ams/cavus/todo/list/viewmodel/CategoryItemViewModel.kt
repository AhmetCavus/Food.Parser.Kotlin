package com.ams.cavus.todo.list.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ams.cavus.todo.BR
import com.ams.cavus.todo.list.model.ProductCategory

class CategoryItemViewModel(val item: ProductCategory) : BaseObservable(){

    var id: String = item.id
        @Bindable get
        set(value) {
            field = value
            item.id = value
            notifyPropertyChanged(BR.id)
        }

    var productCategoryId: String = item.productCategoryId.toString()
        @Bindable get
        set(value) {
            field = value
            item.productCategoryId = value.toInt()
            notifyPropertyChanged(BR.productCategoryId)
        }

    var parentId: String = item.parentId.toString()
        @Bindable get
        set(value) {
            field = value
            item.parentId = value.toInt()
            notifyPropertyChanged(BR.parentId)
        }

    var subcategoryId: String = item.subcategoryId.toString()
        @Bindable get
        set(value) {
            field = value
            item.subcategoryId = value.toInt()
            notifyPropertyChanged(BR.subcategoryId)
        }

    var name: String = item.name
        @Bindable get
        set(value) {
            field = value
            item.name = value
            notifyPropertyChanged(BR.name)
        }

    var count: String = item.count.toString()
        @Bindable get
        set(value) {
            field = value
            item.count = value.toInt()
            notifyPropertyChanged(BR.count)
        }

}