package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class ProductItem(
        @com.google.gson.annotations.SerializedName("id")
        var id: String) {

    @com.google.gson.annotations.SerializedName("productItemId")
    var productItemId = 0

    override fun toString() = "$id/$productItemId"

    override fun equals(other: Any?): Boolean {
        return other is ProductItem && other.id === id
    }

}