package com.swissborg.cryptomarket.features.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.swissborg.cryptomarket.compose.keyboard.keyboardAsState
import com.swissborg.cryptomarket.coroutines.collectAsStateValue
import com.swissborg.cryptomarket.features.main.viewmodel.MainViewModel
import com.swissborg.cryptomarket.navigation.ApplicationGraph
import com.swissborg.cryptomarket.navigation.LocalRouter

@Composable
fun MainHost(viewModel: MainViewModel) {

    val router = LocalRouter.current

    val state = viewModel.state.collectAsStateValue()

    LaunchedEffect(state) {
        if (true == state?.isBackPressed) {
            router.pop()
        }
        viewModel.resetState()
    }

    val isKeyboardOpened = keyboardAsState().value.isOpened()

    var scaffoldModifier = Modifier.statusBarsPadding()
    if (!isKeyboardOpened) scaffoldModifier = scaffoldModifier.navigationBarsWithImePadding()

    ProvideWindowInsets {
        Scaffold(
            modifier = scaffoldModifier,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) { ApplicationGraph() }
            }
        )
    }
}