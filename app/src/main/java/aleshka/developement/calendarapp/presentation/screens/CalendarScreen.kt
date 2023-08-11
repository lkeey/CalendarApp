package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.States.PlanState
import aleshka.developement.calendarapp.events.Event
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarScreen(
    state: PlanState,
    onEvent: (Event) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(Event.CreatePlan)
                }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
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
            items(state.plans) { plan ->
                Row(
                    modifier = Modifier.fillMaxSize()
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
        }
    }
}
