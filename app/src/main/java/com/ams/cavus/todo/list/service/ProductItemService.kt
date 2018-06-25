package com.ams.cavus.todo.list.service

import com.ams.cavus.todo.db.service.AzureEntityService
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.ProductItem
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType

class ProductItemService(client: MobileServiceClient, gson: Gson, settings: Settings) : AzureEntityService<ProductItem>(client, gson, settings) {

    override fun onGetTableName() = "productItem"

    override fun onQueryId() = "productItemQuery"

    override fun onCreateDefinition(): Map<String, ColumnDataType> =
        mapOf(
            "id" to ColumnDataType.Integer,
            "productItemId" to ColumnDataType.Integer
        )

    override fun onCreateTable(): MobileServiceSyncTable<ProductItem> = client.getSyncTable(ProductItem::class.java)

}