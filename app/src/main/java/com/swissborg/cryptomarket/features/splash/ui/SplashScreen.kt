package com.swissborg.cryptomarket.features.splash.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.swissborg.cryptomarket.compose.layout.CenteredColumn
import com.swissborg.cryptomarket.coroutines.collectAsStateValue
import com.swissborg.cryptomarket.features.splash.resources.SplashTexts
import com.swissborg.cryptomarket.features.splash.viewmodel.SplashViewModel
import com.swissborg.cryptomarket.navigation.Host
import com.swissborg.cryptomarket.navigation.LocalRouter

val SplashScreen = Host(route = "SplashScreen")

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {

    val viewModelState = viewModel.state.collectAsStateValue()
    val router = LocalRouter.current

    LaunchedEffect(viewModelState) {
        viewModelState?.let { state ->
            if (!state.isLoading) {
                router.goToTickerList()
            }
        }
    }

    CenteredColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(SplashTexts.SPLASH_SCREEN_TEST_TAG)
    ) { SplashLogo() }
}