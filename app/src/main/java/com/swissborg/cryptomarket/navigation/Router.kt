package com.swissborg.cryptomarket.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.swissborg.cryptomarket.features.ticker.presentation.ui.TickerListScreen

val LocalRouter =
    compositionLocalOf<Router> { error("LocalComposition Router not present") }

class Router(private val navController: NavHostController) {

    fun goToTickerList() = navController.navigate(TickerListScreen)

    fun pop() = navController.popBackStack()

    fun controller() = navController
}