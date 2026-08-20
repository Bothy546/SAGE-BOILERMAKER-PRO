package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AngleCalculator() {
    var length by remember { mutableStateOf("") }
    var massPerMetre by remember { mutableStateOf("") }

    NumField("Length (m)", length) { length = it }
    NumField("Mass per metre (kg/m, from steel table)", massPerMetre) { massPerMetre = it }

    val l = length.toDoubleOrNull()
    val m = massPerMetre.toDoubleOrNull()

    if (l != null && m != null && l > 0 && m > 0) {
        val totalWeight = l * m
        val weightText = "Estimated Total Weight: " + String.format("%.2f", totalWeight) + " kg"
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(weightText, fontWeight = FontWeight.Bold)
                Text("Formula: Total Weight = Length x Mass per Metre", style = MaterialTheme.typography.bodySmall)
                Text("Look up mass per metre for your angle size from a steel section table.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (length.isNotEmpty() || massPerMetre.isNotEmpty()) {
        Text("Enter valid positive numbers.", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}
