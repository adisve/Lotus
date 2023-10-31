package android.app.lotus.utils

import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument

fun getUserProperty(user: User, property: String): String {
    return user.customDataAsBsonDocument()?.get(property)?.asString()?.value ?: ""
}