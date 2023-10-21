package android.app.lotus.data

import android.app.lotus.domain.models.article
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun getArticleList(): Flow<List<article>>
    fun pauseSync()
    fun resumeSync()
    fun close()
}