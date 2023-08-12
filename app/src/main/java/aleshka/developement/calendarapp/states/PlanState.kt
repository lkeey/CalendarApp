package aleshka.developement.calendarapp.states

import aleshka.developement.calendarapp.models.PlanModel

data class PlanState (
    val plans: List<PlanModel> = emptyList(),

    val title: String = "",
    val date: String = "",
    val subject: String = "",
    val color: String = "",

    val isAddingPlan: Boolean = false,
)
