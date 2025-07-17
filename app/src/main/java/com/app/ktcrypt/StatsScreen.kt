package com.app.ktcrypt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.app.ktcrypt.utils.ktCryptFloat
import com.app.ktcrypt.utils.ktCryptInt

class GameStatsViewModel : ViewModel() {
    var score by mutableIntStateOf(10) // not encryoted
    var health by ktCryptInt(100) // encrypted
    //var speed by ktCryptFloat(1.0f) // encrypted
}

@Composable
fun StatsScreen(viewModel: GameStatsViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Game Stats", style = MaterialTheme.typography.headlineMedium)

        Text("Score (not encrypted): ${viewModel.score}")
        Button(onClick = {
            viewModel.score += 10
        }) {
            Text("Add 10 Score")
        }

        Text("Health (encrypted in-memory): ${viewModel.health}")
        Button(onClick = {
            viewModel.health -= 5
        }) {
            Text("Take Damage")
        }
    }
}