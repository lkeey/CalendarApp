package aleshka.developement.calendarapp.view_models

import aleshka.developement.calendarapp.States.PlanState
import aleshka.developement.calendarapp.data.database.PlansDatabase
import aleshka.developement.calendarapp.events.Event
import aleshka.developement.calendarapp.models.PlanModel
import aleshka.developement.calendarapp.repositories.PlansRepository
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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

    private val _state = MutableStateFlow(PlanState())
    val state = combine(_state, _plans) { state, plans ->
        state.copy(
            plans = plans
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        PlanState()
    )

    fun onEvent(
        event: Event
    ) {
        when (event) {
            Event.CreatePlan -> {
                val title = state.value.title
                val date = state.value.date

                if (title.isBlank() || date.isBlank()) {
                    return
                }

                val plan = PlanModel (
                    title = title,
                    date = date
                )

                viewModelScope.launch {
                    repository.addPlan(plan = plan)
                }

                _state.update {
                    it.copy(
                        title = "",
                        date = ""
                    )
                }
            }
            is Event.OnDateUpdated -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is Event.OnTitleUpdated -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            is Event.DeletePlan -> {
                viewModelScope.launch {
                    repository.deletePlan(event.plan)
                }
            }
        }
    }


}
