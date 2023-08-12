package com.mabn.calendarlibrary.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import aleshka.developement.calendarapp.presentation.core.CalendarTheme
import java.time.YearMonth
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthText(selectedMonth: YearMonth, theme: CalendarTheme, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        selectedMonth.month.getDisplayName(
            TextStyle.FULL_STANDALONE,
            context.resources.configuration.locales[0]
        ).uppercase() + " " + selectedMonth.year,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = theme.headerTextColor,
        modifier = modifier
    )
}