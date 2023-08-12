package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.States.PlanState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabn.calendarlibrary.core.CalendarTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle

/**
 * View that represent one day in the calendar
 * @param date date that view should represent
 * @param weekDayLabel flag that indicates if name of week day should be visible above day value
 * @param modifier view modifier
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayView(
    date: LocalDate,
    onDayClick: (LocalDate) -> Unit,
    theme: CalendarTheme,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    weekDayLabel: Boolean = true,
    state: PlanState
) {

    val isCurrentDay = date == LocalDate.now()

    val dayValueModifier =

        // if current day

        if (isCurrentDay) {
            modifier.background(
                theme.selectedDayBackgroundColor.copy(alpha = 0.5f),
                shape = theme.dayShape
            )
        }

        // if this day is selected by user

        else if (isSelected) {
            modifier.background(
                theme.selectedDayBackgroundColor,
                shape = theme.dayShape
            )
        }

        // if it is typically day

        else {
            modifier.background(
                theme.dayBackgroundColor,
                shape = theme.dayShape
            )
        }

    val toDos = state.plans.filter {
        it.date == date.toString()
    }

    Column(
        modifier = Modifier
            .heightIn(max =
                if (weekDayLabel) {
                    50.dp + 50.dp
                } else 50.dp
            )
            .widthIn(max = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // show weekDay (Mon, Tue)

        if (weekDayLabel) {
            Text(
                DayOfWeek.values()[date.dayOfWeek.value - 1].getDisplayName(
                    TextStyle.SHORT,
                    LocalContext.current.resources.configuration.locales[0]
                ),
                fontSize = 10.sp,
                color = theme.weekDaysTextColor
            )
        }

        Text (
//            ${state.plans.getOrNull(0)?.date}
            text = "${toDos}"
        )

        Box(
            dayValueModifier
                .padding(5.dp)
                .aspectRatio(1f)
                .clickable {
                    onDayClick(date)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                date.dayOfMonth.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color =
                    if (isSelected || isCurrentDay) {
                        theme.selectedDayValueTextColor
                    } else {
                        theme.dayValueTextColor
                    }
            )
        }
    }
}
