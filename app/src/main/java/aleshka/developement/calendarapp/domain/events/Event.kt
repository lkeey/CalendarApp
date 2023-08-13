package aleshka.developement.calendarapp.domain.events

import aleshka.developement.calendarapp.data.models.PlanModel

sealed interface Event {
    object CreatePlan: Event
    object ShowCreatingSheet: Event
    object HideCreatingSheet: Event
    object CancelSearching: Event
    data class DeletePlan(val plan: PlanModel): Event
    data class OnTitleUpdated(val title: String): Event
    data class OnDateUpdated(val date: String): Event
    data class OnSubjectUpdated(val subject: String): Event
    data class OnColorUpdated(val color: String): Event
    data class OnSearchQueryUpdated(val searchQuery: String): Event
    data class OnTypeUpdated(val type: String): Event
    data class OnFavouriteClick(val isFavourite: Boolean): Event
    data class OnFavouritesShowing(val isShowing: Boolean): Event
}