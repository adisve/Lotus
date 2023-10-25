package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.services.DataService
import android.app.lotus.domain.models.realm.user
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonString
import javax.inject.Inject


enum class StatisticsStatus {
    Loading,
    Empty,
    Populated
}

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val dataService: DataService
) : ViewModel() {
    private val _userList = mutableListOf<user>()
    val userList: List<user> get() = _userList

    private val _status = MutableLiveData<StatisticsStatus>(StatisticsStatus.Loading)
    val status: LiveData<StatisticsStatus> get() = _status

    private val user = app.currentUser!!
    private val bsonData = user.customDataAsBsonDocument()!!

    init {
        observeCompanyUsersList()
    }

    private fun observeCompanyUsersList() {
        viewModelScope.launch {
            dataService.getCompanyUsers(
                (bsonData.get("company") as BsonString).value
            )
                .collect { event: ResultsChange<user> ->
                    handleCompanyUsersListEvent(event)
                }
        }
    }

    private fun handleCompanyUsersListEvent(event: ResultsChange<user>) {
        Log.i("ArticleViewModel", "Event: $event")
        when (event) {
            is InitialResults -> {
                updateInitialState(event.list)
                _status.value =
                    if (event.list.isEmpty()) StatisticsStatus.Loading else StatisticsStatus.Populated
            }

            is UpdatedResults -> {
                updateChangedState(event)
                _status.value =
                    if (_userList.isEmpty()) StatisticsStatus.Empty else StatisticsStatus.Populated
            }

            else -> {
                _status.value = StatisticsStatus.Loading
            }
        }
    }


    private fun updateInitialState(list: List<user>) {
        _userList.clear()
        _userList.addAll(list)
        Log.i("ArticleViewModel", "InitialResults: ${_userList.toList()}")
    }

    private fun updateChangedState(event: UpdatedResults<user>) {
        applyDeletions(event.deletions)
        applyInsertions(event.insertions, event.list)
        applyChanges(event.changes, event.list)

        Log.i("ArticleViewModel", "UpdatedResult: ${_userList.toList()}")
    }

    private fun applyDeletions(deletions: IntArray) {
        if (deletions.isNotEmpty() && _userList.isNotEmpty()) {
            deletions.reversed().forEach { index ->
                _userList.removeAt(index)
            }
        }
    }

    private fun applyInsertions(insertions: IntArray, list: List<user>) {
        if (insertions.isNotEmpty()) {
            insertions.forEach { index ->
                _userList.add(index, list[index])
            }
        }
    }

    private fun applyChanges(changes: IntArray, list: List<user>) {
        if (changes.isNotEmpty()) {
            changes.forEach { index ->
                _userList[index] = list[index]
            }
        }
    }
}