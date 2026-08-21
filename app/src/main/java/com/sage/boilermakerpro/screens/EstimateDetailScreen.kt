package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Estimate
import com.sage.boilermakerpro.data.calculateTotal

@Composable
fun EstimateDetailScreen(estimate: Estimate, onBack: () -> Unit) {
    var materialCost by remember { mutableStateOf(estimate.materialCost) }
    var materialQty by remember { mutableStateOf(estimate.materialQty) }
    var labourHours by remember { mutableStateOf(estimate.labourHours) }
    var labourRate by remember { mutableStateOf(estimate.labourRate) }
    var consumablesCost by remember { mutableStateOf(estimate.consumablesCost) }
    var transportCost by remember { mutableStateOf(estimate.transportCost) }
    var otherCost by remember { mutableStateOf(estimate.otherCost) }
    var markupPercent by remember { mutableStateOf(estimate.markupPercent) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("Back") }
        }
        Text(estimate.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = materialCost,
            onValueChange = { materialCost = it; estimate.materialCost = it },
            label = { Text("Material Cost (per unit)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = materialQty,
            onValueChange = { materialQty = it; estimate.materialQty = it },
            label = { Text("Material Quantity") },
            modifier = Modifier.fillMaxW
