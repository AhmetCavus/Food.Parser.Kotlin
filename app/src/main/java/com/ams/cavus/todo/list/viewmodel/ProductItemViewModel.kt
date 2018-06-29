package com.ams.cavus.todo.list.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ams.cavus.todo.BR
import com.ams.cavus.todo.list.model.GenericItem

class ProductItemViewModel(val item: GenericItem) : BaseObservable(){

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

    var name: String = item.name
        @Bindable get
        set(value) {
            field = value
            item.name = value
            notifyPropertyChanged(BR.name)
        }

}