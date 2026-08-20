package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("SAGE", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text("BOILERMAKER PRO", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16.dp))
        Text("Welcome back. Ready to fabricate?")
        Spacer(Modifier.height(24.dp))
        Text("Developed by Melvin Chikambure", style = MaterialTheme.typography.labelSmall)
    }
}
