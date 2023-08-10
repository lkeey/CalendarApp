package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.view_models.CalendarViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mabn.calendarlibrary.component.MonthText
import com.mabn.calendarlibrary.core.CalendarIntent
import com.mabn.calendarlibrary.core.CalendarTheme
import com.mabn.calendarlibrary.core.Period
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import com.mabn.calendarlibrary.utils.getWeekStartDate
import java.time.LocalDate
import java.time.YearMonth
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * @param dayShape - to set shape
 * @param backgroundColor - to set background color of the calendar
 * @param selectedDayBackgroundColor - to set background color of the chosen day
 * @param dayValueTextColor - to set text color of the day
 * @param selectedDayValueTextColor - to set text color of chosen day
 * @param headerTextColor - to set text color of the title
 * @param weekDaysTextColor - to set text color of the weekdays
 * */

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun ExpandableCalendar(
    onDayClick: (LocalDate) -> Unit,
    theme: CalendarTheme = calendarDefaultTheme
) {
    val viewModel: CalendarViewModel = viewModel()
    val loadedDates = viewModel.visibleDates.collectAsState()
    val selectedDate = viewModel.selectedDate.collectAsState()
    val calendarExpanded = viewModel.calendarExpanded.collectAsState()
    val currentMonth = viewModel.currentMonth.collectAsState()

    ExpandableCalendar(
        loadedDates = loadedDates.value,
        selectedDate = selectedDate.value,
        currentMonth = currentMonth.value,
        onIntent = viewModel::onIntent,
        calendarExpanded = calendarExpanded.value,
        theme = theme,
        onDayClick = onDayClick
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ExpandableCalendar(
    loadedDates: Array<List<LocalDate>>,
    selectedDate: LocalDate,
    currentMonth: YearMonth,
    calendarExpanded: Boolean,
    theme: CalendarTheme,
    onIntent: (CalendarIntent) -> Unit,
    onDayClick: (LocalDate) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .animateContentSize()
            .background(theme.backgroundColor)
    ) {

    /*
        * show title
    */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .background(Color.Blue)
        ) {

            Spacer(Modifier.weight(1f))

            MonthText(selectedMonth = currentMonth, theme = theme)

            Spacer(Modifier.weight(1f))

            ToggleExpandCalendarButton(
                isExpanded = calendarExpanded,
                expand = { onIntent(CalendarIntent.ExpandCalendar) },
                collapse = { onIntent(CalendarIntent.CollapseCalendar) },
                color = theme.headerTextColor
            )
        }

        if (calendarExpanded) {
            MonthViewCalendar(
                loadedDates,
                selectedDate,
                theme = theme,
                currentMonth = currentMonth,
                loadDatesForMonth = { yearMonth ->
                    onIntent(
                        CalendarIntent.LoadNextDates(
                            yearMonth.atDay(
                                1
                            ), period = Period.MONTH
                        )
                    )
                },
                onDayClick = {
                    onIntent(CalendarIntent.SelectDate(it))
                    onDayClick(it)
                }
            )

        } else {
            InlineCalendar(
                loadedDates,
                selectedDate,
                theme = theme,
                loadNextWeek = { nextWeekDate -> onIntent(CalendarIntent.LoadNextDates(nextWeekDate)) },
                loadPrevWeek = { endWeekDate ->
                    onIntent(
                        CalendarIntent.LoadNextDates(
                            endWeekDate.minusDays(1).getWeekStartDate()
                        )
                    )
                },
                onDayClick = {
                    onIntent(CalendarIntent.SelectDate(it))
                    onDayClick(it)
                }
            )
        }
    }
}
