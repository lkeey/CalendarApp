package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.presentation.component.feature_calendar.ExpandableCalendar
import aleshka.developement.calendarapp.presentation.component.feature_current_day.CurrentDay
import aleshka.developement.calendarapp.presentation.component.feature_favourites.Favourites
import aleshka.developement.calendarapp.presentation.component.feature_searching.Searching
import aleshka.developement.calendarapp.presentation.theme.CalendarAppTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen (
    state: PlanState,
    onEvent: (Event) -> Unit,
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
                    },
                    containerColor = Color(0xFF3579F8),
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add plan"
                    )
                }
            }
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F8FF)),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                item {

                /*
                    Calendar
                */
                    ExpandableCalendar (
                        state = state,
                        onDayClick = {
                            currentDate.value = it
                        },
                        onEvent = onEvent
                    )
                }

                if (state.isSearching) {

                    /*
                        Searching
                    */

                    item {
                        Searching(
                            state = state,
                            onEvent = onEvent
                        )
                    }

                } else if (state.isShowingFavourites) {

                    /*
                        Favourites
                    */

                    item {
                        Favourites(
                            state = state,
                            onEvent = onEvent
                        )
                    }

                } else  {

                    /*
                        Chosen Day
                    */

                    item {
                        CurrentDay(
                            currentDate = currentDate,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }
            }
        }
    }
}
