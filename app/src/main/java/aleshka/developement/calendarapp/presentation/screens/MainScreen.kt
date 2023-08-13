package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.presentation.sheets.CreatePlan
import aleshka.developement.calendarapp.domain.states.PlanState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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

        CalendarScreen (
            state = state,
            onEvent = onEvent,
        )
    }
}
