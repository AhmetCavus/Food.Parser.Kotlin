package com.ams.cavus.todo.list.model

/**
 * Represents an item in a User list
 */
data class Identity(
        var userId: String,
        var name: String) {

    lateinit var id: String

    override fun toString() = "$name/$userId"

    override fun equals(other: Any?): Boolean {
        return other is Identity && other.id === id
    }

}