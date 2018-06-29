package com.ams.cavus.todo.list.model

import java.util.*

/**
 * Represents an item in a User list
 */
data class Store(
        var id: String) {

    var storeId = ""

    lateinit var effectiveDateTime: Date

    var name = ""

    var street = ""

    override fun toString() = "$id/$storeId"

    override fun equals(other: Any?): Boolean {
        return other is Store && other.id === id
    }

}