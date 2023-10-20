package android.app.lotus.domain.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class User() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var email: String = ""
    var name: String = ""
    var password: String = ""
    var role: String = ""
}