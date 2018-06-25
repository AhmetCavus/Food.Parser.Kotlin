package com.ams.cavus.todo.list.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.ams.cavus.todo.BR
import com.ams.cavus.todo.list.model.GenericItem

class GenericItemViewModel(private val genericItem: GenericItem) : BaseObservable(){

    var id: String = genericItem.id
        @Bindable get
        set(value) {
            field = value
            genericItem.id = value
            notifyPropertyChanged(BR.id)
        }

    var name: String = genericItem.name
        @Bindable get
        set(value) {
            field = value
            genericItem.name = value
            notifyPropertyChanged(BR.text)
        }

}