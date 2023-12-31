package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.presentation.component.feature_create.CreatePlan
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    state: PlanState,
    onEvent: (Event) -> Unit
) {

    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {

            // to follow changes and send them in view model

            if (it == ModalBottomSheetValue.Hidden) {
                onEvent(Event.HideCreatingSheet)
            } else {
                onEvent(Event.ShowCreatingSheet)
            }

            true
        },
        skipHalfExpanded = false
    )

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetShape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp
        ),
        sheetContent = {

            // Create Plan Sheet
            CreatePlan(
                state = state,
                onEvent = onEvent
            )
        },
    ) {

        // to open creating sheet
        if (state.isAddingPlan) {
            LaunchedEffect(key1 = true, block = {
                coroutineScope.launch {
                    bottomState.hide()
                    bottomState.show()
                }
            })
        } else {
            LaunchedEffect(key1 = true, block = {
                coroutineScope.launch {
                    bottomState.hide()
                }
            })
        }

        // Screen with calendar
        CalendarScreen (
            state = state,
            onEvent = onEvent,
        )
    }
}
