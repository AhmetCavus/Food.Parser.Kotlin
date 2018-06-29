package com.ams.cavus.todo.list.model

import java.util.*

/**
 * Represents an item in a User list
 */
data class Identity(
        var userId: String,
        var userName: String) {

    var id: String = System.currentTimeMillis().toString()

    var createdAt = Date()

    var deleted: Boolean = false

    override fun toString() = "$userName/$userId"

    override fun equals(other: Any?): Boolean {
        return other is Identity && other.id === id
    }

}