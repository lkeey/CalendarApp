package aleshka.developement.calendarapp.presentation.activities

import aleshka.developement.calendarapp.domain.view_models.PlansViewModel
import aleshka.developement.calendarapp.presentation.screens.MainScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = getViewModel<PlansViewModel>()
            val state by viewModel.state.collectAsState()

            MainScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}
