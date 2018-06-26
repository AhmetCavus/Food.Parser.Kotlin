package com.ams.cavus.todo.list.service

import com.ams.cavus.todo.db.service.AzureEntityService
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.GenericItem
import com.ams.cavus.todo.list.model.ProductCategory
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType

class ProductCategoryService(client: MobileServiceClient, gson: Gson, settings: Settings) : AzureEntityService<ProductCategory>(client, gson, settings) {

    override fun onGetTableName() = "productCategory"

    override fun onQueryId() = "productCategoryQuery"

    override fun onCreateDefinition(): Map<String, ColumnDataType> =
        mapOf(
            "id" to ColumnDataType.String,
            "name" to ColumnDataType.String,
            "subcategoryId" to ColumnDataType.Integer,
            "parentId" to ColumnDataType.Integer,
            "productCategoryId" to ColumnDataType.Integer
        )

    override fun onCreateTable(): MobileServiceSyncTable<ProductCategory> = client.getSyncTable(ProductCategory::class.java)

}