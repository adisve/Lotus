package android.app.lotus.domain.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class article : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var content: String = ""
    var role: String? = null
    var title: String = ""
}
