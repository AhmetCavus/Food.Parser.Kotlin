package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class ProductItem(
        var id: String) {

    var productItemId = 0

    override fun toString() = "$id/$productItemId"

    override fun equals(other: Any?): Boolean {
        return other is ProductItem && other.id === id
    }

}