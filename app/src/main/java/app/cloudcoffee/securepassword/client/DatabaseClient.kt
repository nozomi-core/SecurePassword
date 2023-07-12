package app.cloudcoffee.securepassword.client

import app.cloudcoffee.securepassword.framework.Maybe

data class ObjectCollection(val path: String)
data class ObjectResponse(val path: ObjectPath)
data class ObjectPath(val fullPath: String)

interface DatabaseClient {
    suspend fun insert(collection: ObjectCollection, value: Any): Maybe<ObjectResponse>
    suspend fun whereEqualTo(collection: ObjectCollection, key: String, value: Any): Maybe<QueryResponse>
    suspend fun whereAny(collection: ObjectCollection): Maybe<QueryResponse>
    suspend fun set(path: ObjectPath, value: Any): Maybe<Unit>
}