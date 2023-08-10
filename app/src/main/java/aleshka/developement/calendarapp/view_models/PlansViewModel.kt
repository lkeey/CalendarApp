package aleshka.developement.calendarapp.view_models

import aleshka.developement.calendarapp.data.database.PlansDatabase
import aleshka.developement.calendarapp.models.PlanModel
import aleshka.developement.calendarapp.repositories.PlansRepository
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PlansViewModel (
    context: Context
) : ViewModel() {

    companion object {
        const val TAG = "ViewModelPlans"
    }

    val _plans: MutableLiveData<List<PlanModel>> = MutableLiveData()
    val plans = _plans.value

    private var database : PlansDatabase = PlansDatabase.invoke(context)
    private var repository : PlansRepository = PlansRepository(database = database)

    init {
        Log.i(TAG, "started 1")
        getPlans()
    }

     fun getPlans() {
         Log.i(TAG, "started 2")

        repository.getPlans().observeForever {
            _plans.value = it

            Log.i(TAG, plans.toString())

            Log.i(TAG, _plans.value.toString())
        }
    }

    suspend fun addArticle(planModel: PlanModel) {
        viewModelScope.launch {
            repository.addPlans(planModel)
        }
    }

}
