package aleshka.developement.calendarapp.presentation.component.feature_calendar

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.domain.view_models.CalendarViewModel
import aleshka.developement.calendarapp.presentation.component.feature_searching.SearchTextField
import aleshka.developement.calendarapp.presentation.core.CalendarIntent
import aleshka.developement.calendarapp.presentation.core.Period
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mabn.calendarlibrary.utils.getWeekStartDate
import java.time.LocalDate

/**
 * dayShape - to set shape
 * backgroundColor - to set background color of the calendar
 * selectedDayBackgroundColor - to set background color of the chosen day
 * dayValueTextColor - to set text color of the day
 * selectedDayValueTextColor - to set text color of chosen day
 * headerTextColor - to set text color of the title
 * weekDaysTextColor - to set text color of the weekdays
 * */

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ExpandableCalendar(
    state: PlanState,
    onDayClick: (LocalDate) -> Unit,
    onEvent: (Event) -> Unit,
) {

    val viewModel: CalendarViewModel = viewModel()
    val loadedDates = viewModel.visibleDates.collectAsState()
    val selectedDate = viewModel.selectedDate.collectAsState()
    val calendarExpanded = viewModel.calendarExpanded.collectAsState()
    val currentMonth = viewModel.currentMonth.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
                .animateContentSize()
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        /*
     search & bookmarks
    */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            SearchTextField(
                state = state,
                onClearFocus = {
                    onEvent(Event.CancelSearching)
                },
                onTextChanged = {

                    viewModel.onIntent(CalendarIntent.CollapseCalendar)

                    onEvent(Event.OnSearchQueryUpdated(it))
                }
            )

            Spacer(modifier = Modifier.width(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .heightIn(max = 52.dp)
                    .background(
                        color = if (state.isShowingFavourites) Color(0xFF3579F8) else Color(
                            0xFFFFFFFF
                        ),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .clickable {
                        onEvent(Event.OnFavouritesShowing(!state.isShowingFavourites))
                    },
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    painter = painterResource(id = R.drawable.ic_calendar_favourite),
                    contentDescription = "bookmark",
                    tint = if (state.isShowingFavourites) Color(0xFFFFFFFF) else Color(
                        0xFF757575
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .clickable(
                    onClick = {
                        onEvent(Event.CancelSearching)
                    }
                )
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(
                        size = 40.dp
                    )
                )
        ) {

        /*
            month & toggle
        */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MonthText(
                    selectedMonth = currentMonth.value
                )

                ToggleExpandCalendarButton(
                    isExpanded = calendarExpanded.value,
                    expand = {
                        viewModel.onIntent(CalendarIntent.ExpandCalendar)
                    },
                    collapse = {
                        viewModel.onIntent(CalendarIntent.CollapseCalendar)
                    },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (calendarExpanded.value) {
                MonthViewCalendar(
                    loadedDates.value,
                    selectedDate.value,
                    currentMonth = currentMonth.value,
                    loadDatesForMonth = { yearMonth ->
                        viewModel.onIntent(
                            CalendarIntent.LoadNextDates(
                                yearMonth.atDay(
                                    1
                                ),
                                period = Period.MONTH
                            )
                        )
                    },
                    onDayClick = {
                        onEvent(Event.CancelSearching)

                        viewModel.onIntent(CalendarIntent.SelectDate(it))
                        onDayClick(it)
                    },
                    state = state
                )
            } else {
                InlineCalendar(
                    loadedDates.value,
                    selectedDate.value,
                    loadNextWeek = { nextWeekDate ->
                        viewModel.onIntent(
                            CalendarIntent.LoadNextDates(nextWeekDate)
                        )
                    },
                    loadPrevWeek = { endWeekDate ->
                        viewModel.onIntent(
                            CalendarIntent.LoadNextDates(
                                endWeekDate.minusDays(1).getWeekStartDate()
                            )
                        )
                    },
                    onDayClick = {
                        onEvent(Event.CancelSearching)

                        viewModel.onIntent(CalendarIntent.SelectDate(it))
                        onDayClick(it)
                    },
                    state = state,
                    currentMonth = currentMonth.value,
                )
            }
        }
    }
}
