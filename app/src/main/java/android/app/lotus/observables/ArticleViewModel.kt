package android.app.lotus.observables

import android.app.lotus.data.DataService
import android.app.lotus.domain.models.article
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ArticleListStatus {
    Loading,
    Empty,
    Populated
}

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val dataService: DataService
) : ViewModel() {

    private val _articleList = mutableListOf<article>()
    val articleList: List<article> get() = _articleList

    private val _status = MutableLiveData<ArticleListStatus>(ArticleListStatus.Loading)
    val status: LiveData<ArticleListStatus> get() = _status

    private val _articleCategories = MutableLiveData<List<String>>()
    val articleCategories: LiveData<List<String>> get() = _articleCategories

    init {
        observeArticleList()
    }

    private fun observeArticleList() {
        viewModelScope.launch {
            dataService.getArticleList()
                .collect { event: ResultsChange<article> ->
                    handleArticleListEvent(event)
                }
        }
    }

    private fun handleArticleListEvent(event: ResultsChange<article>) {
        Log.i("ArticleViewModel", "Event: $event")
        when (event) {
            is InitialResults -> {
                updateInitialState(event.list)
                _status.value =
                    if (event.list.isEmpty()) ArticleListStatus.Loading else ArticleListStatus.Populated
            }

            is UpdatedResults -> {
                updateChangedState(event)
                _status.value =
                    if (_articleList.isEmpty()) ArticleListStatus.Empty else ArticleListStatus.Populated
            }

            else -> {
                _status.value = ArticleListStatus.Loading
            }
        }
        updateArticleCategories()
    }

    private fun updateInitialState(list: List<article>) {
        _articleList.clear()
        _articleList.addAll(list)
        Log.i("ArticleViewModel", "InitialResults: ${_articleList.toList()}")
    }

    private fun updateChangedState(event: UpdatedResults<article>) {
        applyDeletions(event.deletions)
        applyInsertions(event.insertions, event.list)
        applyChanges(event.changes, event.list)

        Log.i("ArticleViewModel", "UpdatedResult: ${_articleList.toList()}")
    }

    private fun applyDeletions(deletions: IntArray) {
        if (deletions.isNotEmpty() && _articleList.isNotEmpty()) {
            deletions.reversed().forEach { index ->
                _articleList.removeAt(index)
            }
        }
    }

    private fun applyInsertions(insertions: IntArray, list: List<article>) {
        if (insertions.isNotEmpty()) {
            insertions.forEach { index ->
                _articleList.add(index, list[index])
            }
        }
    }

    private fun applyChanges(changes: IntArray, list: List<article>) {
        if (changes.isNotEmpty()) {
            changes.forEach { index ->
                _articleList[index] = list[index]
            }
        }
    }

    private fun updateArticleCategories() {
        _articleCategories.value = _articleList.map { it.title }.distinct()
    }
}
