package com.swissborg.cryptomarket.navigation

import androidx.compose.runtime.Composable
import com.swissborg.cryptomarket.features.splash.ui.SplashScreen
import com.swissborg.cryptomarket.features.ticker.presentation.ui.TickerListScreen

@Composable
fun ApplicationGraph() {

    val router = LocalRouter.current

    NavGraph(router = router, rootHost = SplashScreen) {

        composable(SplashScreen) {
            SplashScreen()
        }

        composable(TickerListScreen) {
            TickerListScreen()
        }
    }
}