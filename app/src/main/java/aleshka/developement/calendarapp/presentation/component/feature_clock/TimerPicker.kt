package aleshka.developement.calendarapp.presentation.component.feature_clock

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.utils.ClockMarks24h
import aleshka.developement.calendarapp.domain.utils.Time
import aleshka.developement.calendarapp.domain.utils.TimePart
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerPicker(
    onOk: (Time) -> Unit
) {
    var selectedPart by remember {
        mutableStateOf(TimePart.StartHour)
    }

    var selectedHour by remember {
        mutableStateOf(0)
    }
    var selectedMinute by remember {
        mutableStateOf(0)
    }
    var selectedEndHour by remember {
        mutableStateOf(0)
    }
    var selectedEndMinute by remember {
        mutableStateOf(0)
    }

    val selectedTime by remember {
        derivedStateOf {
            when (selectedPart) {
                TimePart.StartHour -> selectedHour
                TimePart.StartMinute -> selectedMinute / 5
                TimePart.EndHour -> selectedEndHour
                TimePart.EndMinute -> selectedEndMinute / 5
            }
        }
    }

    val onTime: (Int) -> Unit = remember {
        {
            when (selectedPart) {
                TimePart.StartHour -> selectedHour = it
                TimePart.StartMinute -> selectedMinute = it * 5
                TimePart.EndHour -> selectedEndHour = it
                TimePart.EndMinute -> selectedEndMinute = it * 5
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFF757575),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp)
    ) {
        Text (
            text = "Выберите время начала и конца",

            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xE5222222),
                letterSpacing = 0.28.sp,
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeCard(
                time = selectedHour,
                isSelected = selectedPart == TimePart.StartHour,
                onClick = { selectedPart = TimePart.StartHour }
            )

            Text(
                text = ":",
                fontSize = 26.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(horizontal = 2.dp)
            )

            TimeCard(
                time = selectedMinute,
                isSelected = selectedPart == TimePart.StartMinute,
                onClick = {
                    selectedPart = TimePart.StartMinute
                }
            )

            Text(
                text = "-",
                fontSize = 26.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(horizontal = 2.dp)
            )

            TimeCard(
                time = selectedEndHour,
                isSelected = selectedPart == TimePart.EndHour,
                onClick = { selectedPart = TimePart.EndHour }
            )

            Text(
                text = ":",
                fontSize = 26.sp,
                color = Color(0xFF757575),
                modifier = Modifier.padding(horizontal = 2.dp)
            )

            TimeCard(
                time = selectedEndMinute,
                isSelected = selectedPart == TimePart.EndMinute,
                onClick = {
                    selectedPart = TimePart.EndMinute
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Clock(
            time = selectedTime,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            ClockMarks24h(selectedPart, selectedTime, onTime)
        }

//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            TextButton(
//                onClick = { onOk(
//                    Time(
//                    selectedHour,
//                    selectedMinute
//                )
//                ) },
//                modifier = Modifier
//                    .width(36.dp)
//                    .height(32.dp)
//            ) {
//                Text(
//                    text = "OK",
//                    fontSize = 10.sp,
//                    color = Color(0xFF3579F8)
//                )
//            }
//        }
    }
}

