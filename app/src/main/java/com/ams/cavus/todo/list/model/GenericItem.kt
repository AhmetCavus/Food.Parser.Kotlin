package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class GenericItem(
        @com.google.gson.annotations.SerializedName("id")
        var id: String) {

    @com.google.gson.annotations.SerializedName("name")
    var name = ""

    @com.google.gson.annotations.SerializedName("productItemId")
    var productItemId = 0

    @com.google.gson.annotations.SerializedName("productCategoryId")
    var productCategoryId = 0

    override fun toString() = "$id/$name"

    override fun equals(other: Any?): Boolean {
        return other is GenericItem && other.id === id
    }

}