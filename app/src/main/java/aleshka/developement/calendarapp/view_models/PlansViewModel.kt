package aleshka.developement.calendarapp.view_models

import aleshka.developement.calendarapp.data.database.PlansDatabase
import aleshka.developement.calendarapp.models.PlanModel
import aleshka.developement.calendarapp.repositories.PlansRepository
import android.content.Context
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlansViewModel (
    context: Context
) : ViewModel() {

    companion object {
        const val TAG = "ViewModelPlans"
    }

    private val _plans = MutableStateFlow<List<PlanModel>>(emptyList())
    val plans = _plans.asStateFlow().value

    private var database : PlansDatabase = PlansDatabase.invoke(context)
    private var repository : PlansRepository = PlansRepository(database = database)

    init {
        Log.i(TAG, "started 1")
        getPlans()
    }

     fun getPlans() {
         Log.i(TAG, "started 2")

         val response = repository.getPlans()
//         _plans.value = response.
    }

    suspend fun addArticle(planModel: PlanModel) {
        viewModelScope.launch {
            repository.addPlans(planModel)
        }
    }

}
