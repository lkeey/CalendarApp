package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.States.PlanState
import aleshka.developement.calendarapp.events.Event
import aleshka.developement.calendarapp.presentation.component.CreatePlan
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CalendarScreen(
    state: PlanState,
    onEvent: (Event) -> Unit
) {

    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        modifier = Modifier
            .wrapContentHeight(),
        sheetState = bottomState,
        sheetShape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp
        ),
        sheetContent = {
            CreatePlan(
                state = state,
                onEvent = onEvent
            )
        },
    ) {
        TestScreen (
            state = state,
            onEvent = onEvent,
            onShowChanged = {
                coroutineScope.launch {

                    Log.i("ViewModelPlans", "show - ${state.isAddingPlan}")

                    if (it) bottomState.show()
                    else bottomState.hide()

                }
            }
        )
    }
}
