package com.kproject.cedronews.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kproject.cedronews.R
import com.kproject.cedronews.presentation.screens.components.CenterTopBar

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToNewsReaderScreen: () -> Unit
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        uiState = uiState,
        onUiEvent = homeViewModel::onUiEvent,
        onNavigateToNewsReaderScreen = onNavigateToNewsReaderScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit,
    onNavigateToNewsReaderScreen: () -> Unit,
) {
    val themeIconResId = if (uiState.isDarkMode) {
        R.drawable.outline_light_mode_24
    } else {
        R.drawable.outline_dark_mode_24
    }
    Scaffold(
        topBar = {
            CenterTopBar(
                title = stringResource(id = R.string.app_name),
                actions = {
                    IconButton(onClick = { onUiEvent.invoke(HomeUiEvent.ChangeAppTheme) }) {
                        Icon(
                            painter = painterResource(id = themeIconResId),
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        MainContent(
            uiState = uiState,
            onUiEvent = onUiEvent,
            onNavigateToNewsReaderScreen = onNavigateToNewsReaderScreen,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit,
    onNavigateToNewsReaderScreen: () -> Unit,
) {

}