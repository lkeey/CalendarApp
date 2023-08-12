package aleshka.developement.calendarapp.States

import aleshka.developement.calendarapp.models.PlanModel

data class PlanState (
    val plans: List<PlanModel> = emptyList(),

    val title: String = "",
    val date: String = "",

    val isAddingPlan: Boolean = false,
)
