package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.states.PlanState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectableText (
    text: String,
    state: PlanState,
    onTextClick: (String) -> Unit
){

    Text (
        modifier = Modifier
            .background(
                color = if (state.isFavourite) Color(0xFFEFF6FF) else Color.White,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(14.dp)
            .clickable {
                 onTextClick(text)
            },
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.regular)),
            fontWeight = FontWeight(400),
            color = if (state.isFavourite) Color(0xE53579F8) else Color(0xE5222222),
            letterSpacing = 0.28.sp,
        )
    )

}
