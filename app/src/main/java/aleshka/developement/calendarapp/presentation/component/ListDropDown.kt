package aleshka.developement.calendarapp.presentation.component

import aleshka.developement.calendarapp.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDropDown(
    subjects: List<String>,
    onTextChanged: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val textValue = remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Row {
                        Text(
                            text = "Предмет",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0x80222222),
                                letterSpacing = 0.3.sp,
                            )
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3579F8),
                    focusedLabelColor = Color(0xFF3579F8),
                    cursorColor = Color(0xFF3579F8),
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                    onTextChanged(it)
                },
                readOnly = true
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                subjects.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(text = item)
                        },
                        onClick = {
                            expanded = false
                            textValue.value = item
                        }
                    )
                }
            }
        }
    }

}
