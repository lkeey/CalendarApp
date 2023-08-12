package aleshka.developement.calendarapp.presentation.component

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

@Composable
fun ColorsBox (
    colors: List<Color>,
    onValueChanged: (Color) -> Unit
) {

    var chosenColor by remember {
        mutableStateOf(colors[0])
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        colors.forEach {
            Box (
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(28.dp)
                    .clickable {
                        chosenColor = it
                        onValueChanged(it)
                    }
                    .border(
                        width = if (chosenColor == it) 0.dp else 4.dp,
                        color = it,
                        CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        color = it,
                        shape = CircleShape
                    )
            )
        }
    }
}
