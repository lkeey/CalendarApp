package aleshka.developement.calendarapp.presentation.component.feature_current_day

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDay (
    currentDate: MutableState<LocalDate>,
    state: PlanState,
    onEvent: (Event) -> Unit
) {

    val currentPlans = state.plans.filter {
        it.date == currentDate.value.toString()
    }

    Column {
        if (currentPlans.isEmpty()) {

            Image (
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_calendar_no_plans),
                contentDescription = "no plans",
                alignment = Alignment.Center
            )

        } else {
            currentPlans.forEach {
                PlanItem(
                    item = it
                ) { plan->
                    onEvent(Event.DeletePlan(plan))
                }
            }
        }
    }

}
