package android.app.lotus.data

import android.app.lotus.app
import android.app.lotus.domain.models.article
import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession
import io.realm.kotlin.mongodb.syncSession
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataService @Inject constructor(
    onSyncError: (session: SyncSession, error: SyncException) -> Unit
) : SyncRepository {

    private lateinit var realm: Realm
    private lateinit var config: SyncConfiguration
    private val currentUser: User
        get() = app.currentUser!!

    init {
        val app = App.create("lotus-bsqvw")
        runBlocking {
            val user = app.login(Credentials.anonymous())
            config = SyncConfiguration.Builder(user, setOf(article::class))
                .initialSubscriptions { realm ->
                    add(
                        realm.query<article>(),
                        "article"
                    )
                }
                .build()
            realm = Realm.open(config)
            Log.v("REALM", "Successfully opened realm: ${realm.configuration.name}")
        }
    }

    override fun getArticleList(): Flow<ResultsChange<article>> {
        return realm.query<article>()
            .sort(Pair("_id", Sort.ASCENDING))
            .asFlow()
    }


    override fun pauseSync() {
        realm.syncSession.pause()
    }

    override fun resumeSync() {
        realm.syncSession.resume()
    }

    override fun close() = realm.close()

}
