package aleshka.developement.calendarapp.presentation.component.feature_create

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.events.Event
import aleshka.developement.calendarapp.domain.states.PlanState
import aleshka.developement.calendarapp.presentation.component.feature_clock.TimerPicker
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CreatePlan(
    state: PlanState,
    onEvent: (Event) -> Unit
) {

    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scroll)
            .padding(vertical = 18.dp, horizontal = 32.dp)
    ) {

        // Top Bar

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Создайте план :)",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF222222),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.4.sp,
                )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_profile_cancel),
                contentDescription = "close",
                modifier = Modifier
                    .clickable {
                        onEvent(Event.HideCreatingSheet)
                    }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Name

        OutlinedText(
            label = "Название",
            onTextChanged = {
                onEvent(Event.OnTitleUpdated(it))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Type

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row {
                SelectableText(
                    text = "Событие",
                    state = state,
                    onTextClick = {
                        onEvent(Event.OnTypeUpdated(it))
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))

                SelectableText(
                    text = "Задача",
                    state = state,
                    onTextClick = {
                        onEvent(Event.OnTypeUpdated(it))
                    }
                )
            }

            // Bookmark

            Box(
                modifier = Modifier
                    .heightIn(max = 46.dp)
                    .background(
                        color = if (state.isFavourite) Color(0xFF3579F8) else Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .clickable {
                        onEvent(Event.OnFavouriteClick(!state.isFavourite))
                    },

                ) {
                Icon(
                    modifier = Modifier
                        .padding(12.dp),
                    painter = painterResource(id = R.drawable.ic_calendar_favourite),
                    contentDescription = "bookmark",
                    tint = if (state.isFavourite) Color(0xFFFFFFFF) else Color(0xFF757575),
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Data
        CalendarInput(
            label = "Дата",
            onTextChanged = {
                onEvent(Event.OnDateUpdated(it))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TimerPicker(
            state = state,
            onEvent = onEvent
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subject

        val subjects = listOf(
            "Астрономия",
            "Английский",
            "Литература",
            "Математика",
            "Информатика",
            "Дизайн",
            "Другое"
        )

        ListDropDown(
            subjects = subjects
        ) {
            Log.i("ViewModelPlans", "subj - $it")

            onEvent(Event.OnSubjectUpdated(it))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Color

        val colorsHex = listOf(
            "#FF7E50FF",
            "#FFFF985E",
            "#FF5D96FF",
            "#FFFF0000",
            "#FF000000",
        )

        ColorsBox(
            colorsHex = colorsHex
        ) {
            onEvent(Event.OnColorUpdated(it))
        }

        Spacer(modifier = Modifier.height(32.dp))

        FilledBtn(
            text = "Сохранить",
            padding = 0.dp
        ) {
            onEvent(Event.CreatePlan)
        }
    }
}
