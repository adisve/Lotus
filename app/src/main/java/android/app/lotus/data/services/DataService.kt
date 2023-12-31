package android.app.lotus.data.services

import android.app.lotus.app
import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.domain.models.realm.article
import android.app.lotus.domain.models.realm.user
import android.app.lotus.domain.models.realm.video
import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataService @Inject constructor(
    onSyncError: (session: SyncSession, error: SyncException) -> Unit
) {

    private lateinit var realm: Realm
    private lateinit var config: SyncConfiguration
    private val currentUser: User
        get() = app.currentUser!!

    init {
        initializeRealm()
    }

    private fun initializeRealm() {
        runBlocking {
            config = SyncConfiguration.Builder(currentUser, setOf(article::class, user::class, video::class))
                .initialSubscriptions { realm ->
                    add(
                        realm.query<article>(),
                        "article"
                    )
                    add(
                        realm.query<user>(),
                        "user"
                    )
                    add(
                        realm.query<video>(),
                        "video"
                    )
                }
                .build()
            realm = Realm.open(config)
            Log.v("Realm", "Successfully opened realm: ${realm.configuration.name}")
        }
    }

    fun getVideoList(): Flow<ResultsChange<video>> {
        return realm.query<video>()
            .sort(Pair("_id", Sort.ASCENDING))
            .asFlow()
    }

    fun getArticleList(): Flow<ResultsChange<article>> {
        return realm.query<article>()
            .sort(Pair("_id", Sort.ASCENDING))
            .asFlow()
    }

    fun getCompanyUsers(company: String): Flow<ResultsChange<user>> {
        return realm.query<user>("company == $0", company)
            .sort(Pair("_id", Sort.ASCENDING))
            .asFlow()
    }

    fun upsertUser(email: String, userFields: Map<String, Any>) {
        runBlocking {
            val existingUser = realm.query<user>("email == $0", email).first().find()
            realm.write {
                val userToUpdate = existingUser ?: user()
                updateUserFields(userToUpdate, userFields)
                if (existingUser == null) {
                    copyToRealm(userToUpdate)
                }
            }
        }
    }


    private fun updateUserFields(user: user, fields: Map<String, Any>) {
        user.apply {
            _id = fields[UserFields.id] as? String
            company = fields[UserFields.company] as? String
            email = fields[UserFields.email] as? String
            phone = fields[UserFields.phone] as? String
            role = fields[UserFields.role] as? String
            username = fields[UserFields.username] as? String
        }
    }

}
