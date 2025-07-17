package com.app.ktcrypt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.ktcrypt.ui.theme.KtCryptTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtCryptTheme {
                val vm: GameStatsViewModel = viewModel()
                StatsScreen(viewModel = vm)
            }
        }
    }
}