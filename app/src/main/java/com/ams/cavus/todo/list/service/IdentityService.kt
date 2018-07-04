package com.ams.cavus.todo.list.service

import com.ams.cavus.todo.db.service.AzureEntityService
import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.Identity
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType
import org.jetbrains.anko.doAsync

typealias AddUserComplete = (Identity) -> Unit

class IdentityService(client: MobileServiceClient, gson: Gson, settings: Settings) : AzureEntityService<Identity>(client, gson, settings) {

    override fun onGetTableName() = "identity"

    override fun onQueryId() = "identityQuery"

    override fun onCreateDefinition(): Map<String, ColumnDataType> =
        mapOf(
            "userId" to ColumnDataType.String,
            "name" to ColumnDataType.String
        )

    override fun onCreateTable(): MobileServiceSyncTable<Identity> = client.getSyncTable(Identity::class.java)

    fun add(userId: String, userName: String, listener: AddUserComplete)
    {
        doAsync {
            initialize()
            var entity = Identity(userId, userName)

            val res = table.insert(entity).get()
            client.syncContext.push().get()
            settings.saveUserName(userName)
            sync {
                listener.invoke(res)
            }
        }
    }

    fun checkUsernameIsValid(name: String, userId: String): Boolean {
        var isValid = false
        if(name.isNotEmpty()) {
            val usernameFound = itemsCache.find { id -> id.name == name }
            val useridFound = itemsCache.find { id -> id.userId == userId }
            isValid = usernameFound == null && useridFound == null
        }
        return isValid
    }

}