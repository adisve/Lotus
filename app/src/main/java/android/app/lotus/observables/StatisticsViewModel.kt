package android.app.lotus.observables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {
    val currentQuestion = MutableLiveData(0)
}