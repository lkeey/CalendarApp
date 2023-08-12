package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.States.PlanState
import aleshka.developement.calendarapp.events.Event
import aleshka.developement.calendarapp.presentation.component.ExpandableCalendar
import aleshka.developement.calendarapp.presentation.theme.CalendarAppTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TestScreen (
    state: PlanState,
    onEvent: (Event) -> Unit,
    onShowChanged: (Boolean) -> Unit
) {
    CalendarAppTheme {

        val currentDate = remember {
            mutableStateOf(LocalDate.now())
        }

        Scaffold (
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onEvent(Event.ShowCreatingSheet)
                        onShowChanged(true)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add plan"
                    )
                }
            }
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                item {
                    ExpandableCalendar (
                        theme = calendarDefaultTheme.copy(
                            dayShape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.White,
                            selectedDayBackgroundColor = Color.Blue,
                            dayValueTextColor = Color.Black,
                            selectedDayValueTextColor = Color.White,
                            headerTextColor = Color.White,
                            weekDaysTextColor = Color.Black
                        ),
                        onDayClick = {
                            currentDate.value = it
                        },
                        state = state,
                    )
                }

                items(state.plans) { plan ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${plan.date} - ${plan.title}",
                                fontSize = 20.sp
                            )
                        }
                        IconButton(onClick = {
                            onEvent(Event.DeletePlan(plan))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete plan"
                            )
                        }
                    }
                }

                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()) {
                        Text("Selected date: ${currentDate.value}")
                    }
                }
            }
        }
    }
}
