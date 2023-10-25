package android.app.lotus.domain.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class video : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var url: String = ""
    var title: String = ""
}