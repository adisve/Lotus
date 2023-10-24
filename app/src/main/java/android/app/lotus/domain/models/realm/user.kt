package android.app.lotus.domain.models.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class user: RealmObject {
    @PrimaryKey
    var _id: String? = null
    var company: String? = null
    var email: String? = null
    var phone: String? = null
    var role: String? = null
    var username: String? = null
}
