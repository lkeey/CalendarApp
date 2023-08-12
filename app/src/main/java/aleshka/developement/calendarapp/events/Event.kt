package aleshka.developement.calendarapp.events

import aleshka.developement.calendarapp.models.PlanModel

sealed interface Event {
    object CreatePlan: Event
    object ShowCreatingSheet: Event
    object HideCreatingSheet: Event
    data class DeletePlan(val plan: PlanModel): Event
    data class OnTitleUpdated(val title: String): Event
    data class OnDateUpdated(val date: String): Event
}