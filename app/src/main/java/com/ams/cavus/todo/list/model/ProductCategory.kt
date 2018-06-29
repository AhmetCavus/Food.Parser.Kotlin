package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class ProductCategory(
        var id: String) {

    var name = ""

    var productItemId = 0

    var productCategoryId = 0

    var subcategoryId = 0

    var parentId = 0

    var count = 0

    override fun toString() = "$id/$name"

    override fun equals(other: Any?): Boolean {
        return other is ProductCategory && other.id === id
    }

}