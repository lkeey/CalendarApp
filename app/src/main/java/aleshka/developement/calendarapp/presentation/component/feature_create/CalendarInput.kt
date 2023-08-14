package aleshka.developement.calendarapp.presentation.component.feature_create

import aleshka.developement.calendarapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarInput(
    label: String,
    onTextChanged: (String) -> Unit
) {

    val calendarState = rememberSheetState()

    val textValue = remember {
        mutableStateOf("")
    }

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {
//            val objects = it.toString().split("-")
//
//            textValue.value = "${objects[2]}.${objects[1]}.${objects[0]}"

            textValue.value = it.toString()

            onTextChanged(textValue.value)
        },
        config = CalendarConfig (
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                calendarState.show()
            },
        trailingIcon = @Composable {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "calendar view"
            )
        },
        label = {
            Row {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF6D7885),
                        letterSpacing = 0.3.sp,
                    )
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3579F8),
            focusedLabelColor = Color(0xFF3579F8),
            cursorColor = Color(0xFF3579F8),
            backgroundColor = White
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it

            onTextChanged(it)
        },
        enabled = false
    )
}
