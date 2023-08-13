package aleshka.developement.calendarapp.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun ColorsBox (
    colorsHex: List<String>,
    onValueChanged: (String) -> Unit
) {

    var chosenColorHex by remember {
        mutableStateOf(colorsHex[0])
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        colorsHex.forEach { hex->
            try {
                val color = Color(hex.toColorInt())
            } catch (ex: Exception) {
                Log.i("ViewModelPlans", "ex - ${ex.message}")
            }

            Box (
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(28.dp)
                    .clickable {
                        chosenColorHex = hex
                        onValueChanged(hex)
                    }
                    .border(
                        width = if (chosenColorHex == hex) 0.dp else 4.dp,
                        color = Color(hex.toColorInt()),
                        CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color(hex.toColorInt()),
                        shape = CircleShape
                    )
            )
        }
    }
}