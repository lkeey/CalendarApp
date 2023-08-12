package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.states.PlanState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mabn.calendarlibrary.component.CalendarPager
import aleshka.developement.calendarapp.presentation.core.CalendarTheme
import com.mabn.calendarlibrary.utils.dayViewModifier
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun MonthViewCalendar(
    loadedDates: Array<List<LocalDate>>,
    selectedDate: LocalDate,
    theme: CalendarTheme,
    currentMonth: YearMonth,
    loadDatesForMonth: (YearMonth) -> Unit,
    onDayClick: (LocalDate) -> Unit,
    state: PlanState
) {

    val itemWidth = LocalConfiguration.current.screenWidthDp / 7

    CalendarPager(
        loadedDates = loadedDates,
        loadNextDates = { loadDatesForMonth(currentMonth) },
        loadPrevDates = { loadDatesForMonth(currentMonth.minusMonths(2)) } // why 2
    ) { currentPage ->

        FlowRow (
            Modifier
                .height(400.dp)
        ) {
            loadedDates[currentPage].forEachIndexed { index, date ->

                Box(
                    Modifier
                        .width(itemWidth.dp)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DayView(
                        date,
                        theme = theme,
                        isSelected = selectedDate == date,
                        onDayClick = {
                            onDayClick(date)
                        },
                        // if index less then 7,
                        // it means that we should show weekDay
                        weekDayLabel = index < 7,
                        modifier = Modifier
                            .dayViewModifier(date, currentMonth, monthView = true),
                        state = state
                    )
                }
            }
        }
    }
}
