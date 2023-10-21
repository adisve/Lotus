package android.app.lotus.observables

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor() : ViewModel() {
    var message: String by mutableStateOf("")
}