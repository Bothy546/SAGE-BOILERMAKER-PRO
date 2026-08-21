package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = labourHours,
            onValueChange = { labourHours = it; estimate.labourHours = it },
            label = { Text("Labour Hours") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = labourRate,
            onValueChange = { labourRate = it; estimate.labourRate = it },
            label = { Text("Labour Rate (per hour)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = consumablesCost,
            onValueChange = { consumablesCost = it; estimate.consumablesCost = it },
            label = { Text("Consumables Cost") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = transportCost,
            onValueChange = { transportCost = it; estimate.transportCost = it },
            label = { Text("Transport Cost") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = otherCost,
            onValueChange = { otherCost = it; estimate.otherCost = it },
            label = { Text("Other Costs") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = markupPercent,
            onValueChange = { markupPercent = it; estimate.markupPercent = it },
            label = { Text("Markup Percent") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        val total = calculateTotal(estimate)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(
                    "Final Quotation: " + String.format("%.2f", total),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
