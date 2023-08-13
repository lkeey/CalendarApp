package aleshka.developement.calendarapp.presentation.screens

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.presentation.component.ExpandableCalendar
import aleshka.developement.calendarapp.presentation.component.PlanItem
import aleshka.developement.calendarapp.presentation.theme.CalendarAppTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

            /*
                Calendar
            */
                item {
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

                    val searchingPlans = state.plans.filter {
                        it.subject.contains(state.searchQuery, ignoreCase = true) ||
                                it.title.contains(state.searchQuery, ignoreCase = true)
                    }

                    if (searchingPlans.isEmpty()) {

                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "По запросу ${state.searchQuery} ничего не найдено",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                    letterSpacing = 0.24.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                    } else {

                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "По запросу ${state.searchQuery} найден(-о) ${searchingPlans.size} план(-ов)",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.medium)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                    letterSpacing = 0.24.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                        items(searchingPlans) {
                            PlanItem(
                                item = it,
                            ) { plan->
                                onEvent(Event.DeletePlan(plan))
                            }
                        }
                    }
                } else if (state.isShowingFavourites) {

                    /*
                        Favourites
                    */

                    val favouritesPlans = state.plans.filter {
                        it.subject.contains(state.searchQuery, ignoreCase = true) ||
                                it.title.contains(state.searchQuery, ignoreCase = true)
                    }

                    if (favouritesPlans.isEmpty()) {
                        items(favouritesPlans) {
                            Image (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                painter = painterResource(id = R.drawable.ic_calendar_no_plans),
                                contentDescription = "no plans",
                                alignment = Alignment.Center
                            )
                        }
                    } else {
//                        item {
//                            Column (
//                                modifier = Modifier
//                                    .padding(horizontal = 16.dp)
//                            ) {
//                                favouritesPlans.forEach {
//                                    PlanItem (
//                                        item = it,
//                                        onDelete = { plan ->
//                                            onEvent(Event.DeletePlan(plan = plan))
//                                        }
//                                    )
//                                }
//                            }
//                        }

                        items(favouritesPlans) {
                            favouritesPlans.forEach {
                                PlanItem (
                                    item = it,
                                    onDelete = { plan ->
                                        onEvent(Event.DeletePlan(plan = plan))
                                    }
                                )
                            }
                        }
                    }

                } else  {

                    /*
                        Chosen Day
                    */

                    val currentPlans = state.plans.filter {
                        it.date == currentDate.value.toString()
                    }

                    item {

                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Column (
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = currentDate.value.dayOfMonth.toString(),
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily(Font(R.font.bold)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF000000),
                                        letterSpacing = 0.24.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Text(
                                    text = currentDate.value.dayOfWeek.toString(),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.medium)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF000000),
                                        letterSpacing = 0.24.sp,
                                    )
                                )
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
                    }
                }
            }
        }
    }
}
