package com.ams.cavus.todo.list.service

import com.ams.cavus.todo.db.service.AzureEntityService
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.Todo
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType
import org.jetbrains.anko.doAsync

class StoreService(client: MobileServiceClient, gson: Gson, settings: Settings) : AzureEntityService<Todo>(client, gson, settings) {

    override fun onGetTableName() = "store"

    override fun onQueryId() = "storeQuery"

    override fun onCreateDefinition(): Map<String, ColumnDataType> =
        mapOf(
            "storeId" to ColumnDataType.String,
            "effectiveDateTime" to ColumnDataType.DateTimeOffset,
            "name" to ColumnDataType.String,
            "street" to ColumnDataType.String,
            "housenumber" to ColumnDataType.String,
            "zip" to ColumnDataType.String,
            "city" to ColumnDataType.String,
            "geolocation" to ColumnDataType.String,
            "storeAreaPlans" to ColumnDataType.String,
            "zoneGraph" to ColumnDataType.String
        )

    override fun onCreateTable(): MobileServiceSyncTable<Todo> = client.getSyncTable(Todo::class.java)

    fun add(userId: String, text: String, listener: AddTodoComplete)
    {
        doAsync {
            initialize()
            var entity = Todo(userId, text)
            val res = table.insert(entity).get()
            sync { listener.invoke(res) }
        }
    }

    fun pushCache(listener: PushComplete)
    {
        doAsync {
            initialize()
            itemsCache.forEach { entity -> table.insert(entity).get() }
            sync { listener.invoke() }
        }
    }
}