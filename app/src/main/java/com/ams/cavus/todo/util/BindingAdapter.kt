package com.ams.cavus.todo.util

import android.databinding.BindingAdapter
import android.databinding.InverseBindingListener
import android.view.View
import android.widget.CheckBox
import android.databinding.InverseBindingAdapter
import android.graphics.Color

@BindingAdapter("onClick")
fun setOnClick(view: com.google.android.gms.common.SignInButton, clickListener: View.OnClickListener) {
    view.setOnClickListener(clickListener)
}

@BindingAdapter("visibleIf")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if(visible) View.VISIBLE else View.GONE
}

@BindingAdapter("backgroundColor")
fun setVisibility(view: View, color: String) {
    var colorRes = Color.parseColor(color)
    view.setBackgroundColor(colorRes)
}

@BindingAdapter("checkedAttrChanged")
fun setListener(cb: CheckBox, listener: InverseBindingListener) {
    if (listener != null) {
        cb.setOnCheckedChangeListener {
            _, isChecked ->
                listener.onChange()
        }
    }
}

@BindingAdapter("checked")
fun setChecked(cb: CheckBox, checked: Boolean) {
    if(cb.isChecked != checked) cb.isChecked = checked
}

@InverseBindingAdapter(attribute = "checked")
fun getChecked(cb: CheckBox): Boolean {
    return cb.isChecked
}

