package com.ams.cavus.todo.db.service

import com.ams.cavus.todo.helper.Settings
import com.ams.cavus.todo.list.model.GenericItem
import com.google.gson.Gson
import com.microsoft.windowsazure.mobileservices.MobileServiceClient
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser
import com.microsoft.windowsazure.mobileservices.table.query.Query
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

typealias SyncCompleteListener<TEntity> = (MutableList<TEntity>) -> Unit

abstract class AzureEntityService<TEntity>(protected val client: MobileServiceClient, protected val gson: Gson, protected var settings: Settings) {

    var itemsCache = mutableListOf<TEntity>()

    protected lateinit var table: MobileServiceSyncTable<TEntity>

    fun fetch(query: Query?, syncCompleteListener: SyncCompleteListener<TEntity>)
    {
        //Initialize & Sync
        sync {
            doAsync {
                itemsCache = table.read(query).get()
                uiThread { syncCompleteListener.invoke(itemsCache) }
            }
        }
    }

    fun sync(callback: () -> Unit)
    {
        doAsync {
            initialize()
            //pull down all latest changes and then push current coffees up
            uiThread { callback() }
            table.pull(null, onQueryId()).get()
//            client.syncContext.push().get()
        }
    }

    fun initialize() {
        if(client.syncContext?.isInitialized == true && !settings.isCredentials) return

        client.apply {
            currentUser = MobileServiceUser(settings.credentials.userId)
            currentUser.authenticationToken = settings.credentials.authToken
        }

        //setup our local sqlite store and intialize our table
        var store = SQLiteLocalStore(client.context, "dbLocal.db", null, 1)

        val definition = onCreateDefinition()

        //Define table
        store.defineTable(onGetTableName(), definition)

        //Initialize SyncContext
        client.syncContext.initialize(store, SimpleSyncHandler()).get()

        //Get our sync table that will call out to azure
        table = onCreateTable()
    }

//    fun test() {
//        //Initialize & Sync
//        sync {
//            doAsync {
//                val items = client.invokeApi("test", mapOf("table" to "genericItems"), )
//                val tmp = items.get()
//            }
//        }
//    }

    abstract fun onCreateTable(): MobileServiceSyncTable<TEntity>
    abstract fun onCreateDefinition(): Map<String, ColumnDataType>
    abstract fun onGetTableName(): String
    abstract fun onQueryId(): String
}