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
import androidx.core.graphics.toColorInt

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

        val colorsHex = listOf(
            "#FF7E50FF",
            "#FFFF985E",
            "#FF5D96FF",
            "#FFFF0000",
            "#FF000000",
        )

        ColorsBox (
            colorsHex = colorsHex
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
