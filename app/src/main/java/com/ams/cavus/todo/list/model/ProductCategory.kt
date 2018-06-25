package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class ProductCategory(
        @com.google.gson.annotations.SerializedName("id")
        var id: Int) {

    @com.google.gson.annotations.SerializedName("name")
    var name = ""

    @com.google.gson.annotations.SerializedName("productItemId")
    var productItemId = 0

    @com.google.gson.annotations.SerializedName("productCategoryId")
    var productCategoryId = 0

    @com.google.gson.annotations.SerializedName("subcategoryId")
    var subcategoryId = 0

    @com.google.gson.annotations.SerializedName("parentId")
    var parentId = 0

    override fun toString() = "$id/$name"

    override fun equals(other: Any?): Boolean {
        return other is ProductCategory && other.id === id
    }

}