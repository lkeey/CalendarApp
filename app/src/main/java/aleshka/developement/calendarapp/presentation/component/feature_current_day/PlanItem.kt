package aleshka.developement.calendarapp.presentation.component.feature_current_day

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.data.models.PlanModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun PlanItem  (
    item: PlanModel,
    onDelete: (PlanModel) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = Color(item.color.toColorInt()),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {

            Text(
                text = item.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.24.sp,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${item.startTime} - ${item.endTime}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.24.sp,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.date,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.24.sp,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.subject,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.24.sp,
                )
            )
        }

        IconButton(
            onClick = {
                onDelete(item)
            },
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete plan",
                tint = Color.White
            )
        }
    }
}
