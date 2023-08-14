package aleshka.developement.calendarapp.presentation.component.feature_clock

import aleshka.developement.calendarapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeCard(
    time: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val timeText =
        if (time == 0) "00"
        else if (time.toString().length == 1) "0$time"
        else time.toString()

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDF4FD),
        ),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp),
            text = timeText,
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = if (isSelected) Color(0xE53579F8) else Color.White,
                letterSpacing = 0.28.sp,
            )
        )
    }
}
