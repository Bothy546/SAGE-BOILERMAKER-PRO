package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeldConsumableCalculator() {
    var weldLength by remember { mutableStateOf("") }
    var legSize by remember { mutableStateOf("") }
    var depositEfficiency by remember { mutableStateOf("65") }

    NumField("Total Weld Length (mm)", weldLength) { weldLength = it }
    NumField("Fillet Leg Size (mm)", legSize) { legSize = it }
    NumField("Deposit Efficiency Percent (default 65)", depositEfficiency) { depositEfficiency = it }

    val l = weldLength.toDoubleOrNull()
    val leg = legSize.toDoubleOrNull()
    val eff = depositEfficiency.toDoubleOrNull()

    if (l != null && leg != null && eff != null && l > 0 && leg > 0 && eff > 0) {
        val steelDensity = 7850.0
        val areaMm2 = 0.5 * leg * leg
        val volumeM3 = (areaMm2 / 1_000_000) * (l / 1000)
        val weldMetalKg = volumeM3 * steelDensity
        val consumableNeededKg = weldMetalKg / (eff / 100)
        val weightText = "Weld Metal Required: " + String.format("%.3f", weldMetalKg) + " kg"
        val consumableText = "Estimated Consumable Needed: " + String.format("%.3f", consumableNeededKg) + " kg"
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(weightText, fontWeight = FontWeight.Bold)
                Text(consumableText, fontWeight = FontWeight.Bold)
                Text("Formula: assumes right-angle fillet, weld metal = 0.5 x Leg^2 x Length x Density", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (weldLength.isNotEmpty() || legSize.isNotEmpty()) {
        Text("Enter valid positive numbers for all fields.")
    }
    DisclaimerNote()
}
