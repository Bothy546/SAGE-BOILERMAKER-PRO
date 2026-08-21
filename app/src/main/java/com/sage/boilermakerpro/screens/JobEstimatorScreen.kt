package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
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
                Text("SAGE Job Estimator", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                IconButton(onClick = { showNewDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = "New Estimate")
                }
            }
            Spacer(Modifier.height(12.dp))

            if (savedEstimates.isEmpty()) {
                Text("No estimates yet. Tap plus to create one.", style = MaterialTheme.typography.bodyMedium)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(savedEstimates) { estimate ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { selectedEstimate = estimate }
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(estimate.name, fontWeight = FontWeight.Bold)
                                val total = calculateTotal(estimate)
                                Text(
                                    "Total: " + String.format("%.2f", total),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showNewDialog) {
        NewEstimateDialog(
            onDismiss = { showNewDialog = false },
            onCreate = { name ->
                savedEstimates.add(Estimate(name = name))
                showNewDialog = false
            }
        )
    }
}
