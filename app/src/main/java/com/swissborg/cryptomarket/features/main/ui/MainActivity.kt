package com.swissborg.cryptomarket.features.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.swissborg.cryptomarket.compose.keyboard.KeyboardManager
import com.swissborg.cryptomarket.compose.keyboard.LocalKeyboardManager
import com.swissborg.cryptomarket.features.main.viewmodel.MainViewModel
import com.swissborg.cryptomarket.navigation.LocalRouter
import com.swissborg.cryptomarket.navigation.Router
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val router = Router(navController)
            val keyboardManager = KeyboardManager(this)

            CompositionLocalProvider(
                LocalRouter provides router,
                LocalKeyboardManager provides keyboardManager
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { MainHost(viewModel) }
            }
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

}