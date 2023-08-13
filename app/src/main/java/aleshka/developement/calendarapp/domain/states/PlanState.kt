package aleshka.developement.calendarapp.domain.states

import aleshka.developement.calendarapp.data.models.PlanModel

data class PlanState (
    val plans: List<PlanModel> = emptyList(),

    val title: String = "",
    val date: String = "",
    val subject: String = "",
    val color: String = "",
    val isFavourite: Boolean = false,

    val isAddingPlan: Boolean = false,
    var isSearching: Boolean = false,
    val isShowingFavourites: Boolean = false,

    val searchQuery: String = ""
)
