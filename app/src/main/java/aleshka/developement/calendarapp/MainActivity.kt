package aleshka.developement.calendarapp

import aleshka.developement.calendarapp.models.PlanModel
import aleshka.developement.calendarapp.presentation.component.ExpandableCalendar
import aleshka.developement.calendarapp.presentation.theme.CalendarAppTheme
import aleshka.developement.calendarapp.view_models.PlansViewModel
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarAppTheme {

                val viewModel = getViewModel<PlansViewModel>()
                val coroutineScope = rememberCoroutineScope()

                val state by viewModel.state.collectAsState()

                val currentDate = remember { mutableStateOf(LocalDate.now()) }
                val scrollState = rememberScrollState()

                Column(Modifier.verticalScroll(scrollState)) {

                    ExpandableCalendar (
                        theme = calendarDefaultTheme.copy(
                            dayShape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.White,
                            selectedDayBackgroundColor = Color.Blue,
                            dayValueTextColor = Color.Black,
                            selectedDayValueTextColor = Color.White,
                            headerTextColor = Color.White,
                            weekDaysTextColor = Color.Black
                        ), onDayClick = {
                            currentDate.value = it
                        }
                    )

                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Selected date: ${currentDate.value}")
                    }

                    Button(onClick = {
                        coroutineScope.launch {
                            viewModel.addArticle(
                                PlanModel(
                                    title = "pLaN",
                                    date = "12/13/14"
                                )
                            )
                        }
                    }) {
                        Text(text = "Add")
                    }

                    Text(text = "${viewModel.plans}")

                }
            }
        }
    }
}
