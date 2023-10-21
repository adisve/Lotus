package android.app.lotus.observables

import android.app.lotus.data.DataService
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val dataService: DataService
) : ViewModel() {

    init {
//        var t = dataService.getArticleList()
//        Log.i("AuthViewModel", t.toString())
    }
}