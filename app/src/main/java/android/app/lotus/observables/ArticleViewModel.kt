package android.app.lotus.observables

import android.app.lotus.data.DataService
import android.app.lotus.domain.models.article
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val dataService: DataService
) : ViewModel() {
    val articleListState: SnapshotStateList<article> = mutableStateListOf()

    init {
        viewModelScope.launch {
            dataService.getArticleList()
                .collect { event: ResultsChange<article> ->
                    when (event) {
                        is InitialResults -> {
                            articleListState.clear()
                            articleListState.addAll(event.list)
                            Log.i(
                                "ArticleViewModel",
                                "InitialResults: ${articleListState.toList().first().content}"
                            )
                        }

                        is UpdatedResults -> {
                            if (event.deletions.isNotEmpty() && articleListState.isNotEmpty()) {
                                event.deletions.reversed().forEach {
                                    articleListState.removeAt(it)
                                }
                            }
                            if (event.insertions.isNotEmpty()) {
                                event.insertions.forEach {
                                    articleListState.add(it, event.list[it])
                                }
                            }
                            if (event.changes.isNotEmpty()) {
                                event.changes.forEach {
                                    articleListState.removeAt(it)
                                    articleListState.add(it, event.list[it])
                                }
                            }
                            Log.i(
                                "ArticleViewModel",
                                "InitialResults: ${articleListState.toList()}"
                            )
                        }

                        else -> Unit // No-op
                    }
                }
        }
    }
}