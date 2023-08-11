package aleshka.developement.calendarapp.events

sealed class Event {
    object CreatePlan: Event()
    data class OnTitleUpdated(val title: String): Event()
    data class OnDateUpdated(val date: String): Event()
}