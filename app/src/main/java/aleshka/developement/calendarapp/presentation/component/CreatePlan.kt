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

        ListDropDown(

        ) {
//            onEvent(Event.)
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
