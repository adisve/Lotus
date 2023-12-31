package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.services.UserService
import android.app.lotus.domain.models.constants.UserFields
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.ext.call
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonDocument
import javax.inject.Inject

@HiltViewModel
class EvaluationViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    fun addEvaluationToUser(evaluation: List<String>) {
        val fieldsToUpdate: Map<String, Any> =
            mapOf(UserFields.evaluation to evaluation, UserFields.evaluated to true)

        viewModelScope.launch {
            userService.writeCustomUserData(fieldsToUpdate)
        }
    }
}