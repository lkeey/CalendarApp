package aleshka.developement.calendarapp.presentation.component.feature_calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CalendarPager(
    loadedDates: Array<List<LocalDate>>,
    loadNextDates: (date: LocalDate) -> Unit,
    loadPrevDates: (date: LocalDate) -> Unit,
    content: @Composable (currentPage: Int) -> Unit
) {
    val initialized = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(initialPage = 1)
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 2) {
            loadNextDates(loadedDates[1][0])
            pagerState.scrollToPage(1)
        }
        if (pagerState.currentPage == 0 && initialized.value) {
            loadPrevDates(loadedDates[0][0])
            pagerState.scrollToPage(1)
        }
    }
    LaunchedEffect(Unit) {
        initialized.value = true
    }
    HorizontalPager(
        pageCount = 3,
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) { currentPage ->
        content(currentPage)
    }
}