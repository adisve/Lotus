package android.app.lotus.observables

import android.app.lotus.data.services.DataService
import android.app.lotus.domain.models.realm.video
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

enum class VideoListStatus {
    Loading,
    Empty,
    Populated
}

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val dataService: DataService
) : ViewModel() {

    private val _videoList = mutableListOf<video>()
    val videoList: List<video> get() = _videoList
    private val _status = MutableLiveData<VideoListStatus>(VideoListStatus.Loading)
    val status: LiveData<VideoListStatus> get() = _status

    init {
        observeVideoList()
    }

    private fun observeVideoList() {
        viewModelScope.launch {
            dataService.getVideoList()
                .collect { event: ResultsChange<video> ->
                    handleVideoListEvent(event)
                }
        }
    }

    private fun handleVideoListEvent(event: ResultsChange<video>) {
        Log.i("VideoViewModel", "Event: ${event::class.simpleName}}")
        when (event) {
            is InitialResults -> {
                updateInitialState(event.list)
                _status.value =
                    if (event.list.isEmpty()) VideoListStatus.Loading else VideoListStatus.Populated
            }

            is UpdatedResults -> {
                updateChangedState(event)
                _status.value =
                    if (_videoList.isEmpty()) VideoListStatus.Empty else VideoListStatus.Populated
            }

            else -> {
                _status.value = VideoListStatus.Loading
            }
        }
        Log.d("VideoViewModel", "Status: ${_status.value}")
    }

    private fun updateInitialState(list: List<video>) {
        _videoList.clear()
        _videoList.addAll(list)
        Log.i("VideoViewModel", "InitialResults: ${_videoList.toList()}")
    }

    private fun updateChangedState(event: UpdatedResults<video>) {
        applyDeletions(event.deletions)
        applyInsertions(event.insertions, event.list)
        applyChanges(event.changes, event.list)

        Log.i("VideoViewModel", "UpdatedResult: ${_videoList.toList()}")
    }

    private fun applyDeletions(deletions: IntArray) {
        if (deletions.isNotEmpty() && _videoList.isNotEmpty()) {
            deletions.reversed().forEach { index ->
                _videoList.removeAt(index)
            }
        }
    }

    private fun applyInsertions(insertions: IntArray, list: List<video>) {
        if (insertions.isNotEmpty()) {
            insertions.forEach { index ->
                _videoList.add(index, list[index])
            }
        }
    }

    private fun applyChanges(changes: IntArray, list: List<video>) {
        if (changes.isNotEmpty()) {
            changes.forEach { index ->
                _videoList[index] = list[index]
            }
        }
    }
}