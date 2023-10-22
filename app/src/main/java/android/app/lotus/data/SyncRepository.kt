package android.app.lotus.data

import android.app.lotus.domain.models.article
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun getArticleList(): Flow<ResultsChange<article>>
    fun pauseSync()
    fun resumeSync()
    fun close()
}