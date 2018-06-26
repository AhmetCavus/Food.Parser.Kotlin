package com.ams.cavus.todo.list.service

import com.ams.cavus.todo.db.service.AzureEntityService
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.GenericItem
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType

class GenericItemService(client: MobileServiceClient, gson: Gson, settings: Settings) : AzureEntityService<GenericItem>(client, gson, settings) {

    override fun onGetTableName() = "genericItem"

    override fun onQueryId() = "genericItemQuery"

    override fun onCreateDefinition(): Map<String, ColumnDataType> =
        mapOf(
            "id" to ColumnDataType.String,
            "name" to ColumnDataType.String,
            "productItemId" to ColumnDataType.Integer,
            "productCategoryId" to ColumnDataType.Integer
        )

    override fun onCreateTable(): MobileServiceSyncTable<GenericItem> = client.getSyncTable(GenericItem::class.java)

}