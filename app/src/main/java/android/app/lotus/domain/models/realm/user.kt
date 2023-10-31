package android.app.lotus.domain.models.realm

import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Required

class user : RealmObject {
    @PrimaryKey
    var _id: String? = null
    var company: String? = null
    var email: String? = null
    var phone: String? = null
    var role: String? = null
    var username: String? = null
    var evaluated: Boolean? = null


    @Required
    var evaluation: RealmList<String> = emptyList<String>().toRealmList()
}
