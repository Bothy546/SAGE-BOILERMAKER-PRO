package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Estimate
import com.sage.boilermakerpro.data.calculateTotal
import com.sage.boilermakerpro.data.savedEstimates

@Composable
fun JobEstimatorScreen() {
    var selectedEstimate by remember { mutableStateOf<Estimate?>(null) }
    var showNewDialog by remember { mutableStateOf(false) }

    if (selectedEstimate != null) {
        EstimateDetailScreen(
            estimate = selectedEstimate!!,
            onBack = { selectedEstimate = null }
        )
    } else {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("SAGE Job Estimator", style = MaterialTheme.typography.headlineSmall, fontWeight = FontW
