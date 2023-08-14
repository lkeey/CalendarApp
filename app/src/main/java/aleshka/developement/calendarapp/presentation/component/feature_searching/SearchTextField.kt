package aleshka.developement.calendarapp.presentation.component.feature_searching

import aleshka.developement.calendarapp.R
import aleshka.developement.calendarapp.domain.states.PlanState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchTextField (
    state: PlanState,
    onClearFocus: () -> Unit,
    onTextChanged: (String) -> Unit
) {

    val textValue = remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    if (!state.isSearching && !state.isAddingPlan) {
        focusManager.clearFocus()
    }

    OutlinedTextField(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(12.dp),
        label = {
            Row {
                Text(
                    text = "Найти...",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF757575),
                        letterSpacing = 0.3.sp,
                    )
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF757575),
            cursorColor = Color(0xFF757575),
            backgroundColor = Color.White,

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onClearFocus()
            focusManager.clearFocus()
        }),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_calendar_search),
                contentDescription = "search"
            )
        }
    )
}
