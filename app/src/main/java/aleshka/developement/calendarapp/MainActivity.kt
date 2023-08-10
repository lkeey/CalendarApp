package aleshka.developement.calendarapp

import aleshka.developement.calendarapp.ui.theme.CalendarAppTheme
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
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarAppTheme {

                val currentDate = remember { mutableStateOf(LocalDate.now()) }
                val scrollState = rememberScrollState()

                Column(Modifier.verticalScroll(scrollState)) {

                    ExpandableCalendar (
                        theme = calendarDefaultTheme.copy(

                            /**
                            * @param dayShape - to set shape
                            * @param backgroundColor - to set background color of the calendar
                            * @param selectedDayBackgroundColor - to set background color of the chosen day
                            * @param dayValueTextColor - to set text color of the day
                            * @param selectedDayValueTextColor - to set text color of chosen day
                            * @param headerTextColor - to set text color of the title
                            * @param weekDaysTextColor - to set text color of the weekdays*/

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
                }
            }
        }
    }
}
