package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.utils.dayViewModifier
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mabn.calendarlibrary.component.CalendarPager
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun MonthViewCalendar(
    loadedDates: Array<List<LocalDate>>,
    selectedDate: LocalDate,
    currentMonth: YearMonth,
    loadDatesForMonth: (YearMonth) -> Unit,
    onDayClick: (LocalDate) -> Unit,
    state: PlanState
) {

    val itemWidth = LocalConfiguration.current.screenWidthDp / 7

    CalendarPager(
        loadedDates = loadedDates,
        loadNextDates = {
            loadDatesForMonth(currentMonth)
        },
        loadPrevDates = {
            loadDatesForMonth(currentMonth.minusMonths(2))
        }
    ) { currentPage ->

        FlowRow(
            modifier = Modifier

                .wrapContentSize()
//                .background(
//                    color = Color(0xFFFFFFFF),
//                    shape = RoundedCornerShape(
//                        bottomEnd = 40.dp,
//                        bottomStart = 40.dp
//                    )
//                )
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Color.Transparent,
//                            Color.Black.copy(alpha = .15f),
//                        )
//                    ),
//                    shape = RoundedCornerShape(
//                        bottomEnd = 40.dp,
//                        bottomStart = 40.dp
//                    )
//                )
//                .zIndex(1f)
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
