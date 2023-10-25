package com.kproject.cedronews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kproject.cedronews.presentation.navigation.NavigationGraph
import com.kproject.cedronews.presentation.screens.home.HomeViewModel
import com.kproject.cedronews.presentation.theme.CedroNewsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            homeViewModel.uiState.value.keepSplashOnScreen
        }
        setContent {
            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
            CedroNewsTheme(isDarkMode = uiState.isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(homeViewModel = homeViewModel)
                }
            }
        }
    }
}