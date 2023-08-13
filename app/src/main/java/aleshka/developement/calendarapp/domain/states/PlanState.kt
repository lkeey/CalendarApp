package aleshka.developement.calendarapp.domain.states

import aleshka.developement.calendarapp.data.models.PlanModel

data class PlanState (
    val plans: List<PlanModel> = emptyList(),

    val title: String = "",
    val date: String = "",
    val subject: String = "",
    val color: String = "",

    val isAddingPlan: Boolean = false,
)
