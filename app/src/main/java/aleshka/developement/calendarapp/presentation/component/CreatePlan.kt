package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.events.Event
import aleshka.developement.calendarapp.states.PlanState
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CreatePlan (
    state: PlanState,
    onEvent: (Event) -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 18.dp, horizontal = 32.dp)
    ) {

        OutlinedText(
            label = "Название",
            onTextChanged = {
                Log.i("ViewModelPlans", "name - ${it}")

                onEvent(Event.OnTitleUpdated(it))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CalendarInput(
            label = "Дата",
            onTextChanged = {
                Log.i("ViewModelPlans", "date - ${it}")

                onEvent(Event.OnDateUpdated(it))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        val subjects = listOf(
            "Астрономия",
            "Английский",
            "Литература",
            "Математика",
            "Информатика",
            "Дизайн",
            "Другое"
        )

        ListDropDown(
            subjects = subjects
        ) {
            Log.i("ViewModelPlans", "subj - $it")

            onEvent(Event.OnSubjectUpdated(it))
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        val colors = listOf(
            Color(0xFF7E50FF),
            Color(0xFFFF985E),
            Color(0xFF5D96FF),
            Color(0xFFFF0000),
            Color(0xFF000000),
       )

        ColorsBox (
            colors = colors
        ) {
            onEvent(Event.OnColorUpdated(it.toString()))
        }

        Spacer(modifier = Modifier.height(8.dp))

        FilledBtn(
            text = "Сохранить",
            padding = 0.dp
        ) {
            onEvent(Event.CreatePlan)
        }
    }
}
