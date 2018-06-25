package com.ams.cavus.todo.list.model

import java.util.*

/**
 * Represents an item in a User list
 */
data class Store(
        @com.google.gson.annotations.SerializedName("id")
        var id: String) {

    @com.google.gson.annotations.SerializedName("storeId")
    var storeId = ""

    @com.google.gson.annotations.SerializedName("effectiveDateTime")
    lateinit var effectiveDateTime: Date

    @com.google.gson.annotations.SerializedName("name")
    var name = ""

    @com.google.gson.annotations.SerializedName("street")
    var street = ""

    override fun toString() = "$id/$storeId"

    override fun equals(other: Any?): Boolean {
        return other is Store && other.id === id
    }

}