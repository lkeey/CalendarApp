package aleshka.developement.calendarapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CalendarAppTheme(
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {

        systemUiController.setSystemBarsColor(
            color = Color(0xFFF4F8FF),
            darkIcons = useDarkIcons
        )

        onDispose {}
    }

    MaterialTheme(
        typography = Typography,
        content = content
    )
}
