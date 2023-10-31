package android.app.lotus.utils

import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import org.mongodb.kbson.BsonDocument
import org.mongodb.kbson.BsonValue

fun User.getProperty(property: String): String {
    return this.customDataAsBsonDocument()?.get(property)?.asString()?.value ?: ""
}

fun User.getPropertyAsArray(property: String): List<String> {
    return this.customDataAsBsonDocument()?.get(property)?.asArray()?.values?.map {
        it.asString().value
    } ?: emptyList()
}

