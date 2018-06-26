package com.ams.cavus.todo.list.adapter

import com.ams.cavus.todo.R
import com.ams.cavus.todo.base.MvvmAdapter
import com.ams.cavus.todo.databinding.ListitemTodoBinding
import com.ams.cavus.todo.list.model.Todo
import com.ams.cavus.todo.list.viewmodel.TodoItemViewModel


class TodoListAdapter: MvvmAdapter<Todo, ListitemTodoBinding>() {

    override fun onGetLayoutRes() = R.layout.listitem_todo

    override fun onBindViewHolder(holder: ItemHolder<ListitemTodoBinding>, position: Int) {
        holder.listItemBinding.vm = TodoItemViewModel(items[position])
    }

}