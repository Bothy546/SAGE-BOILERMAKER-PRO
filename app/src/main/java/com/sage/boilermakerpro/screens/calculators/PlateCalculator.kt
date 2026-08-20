package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PlateCalculator() {
    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var thickness by remember { mutableStateOf("") }
    var density by remember { mutableStateOf("7850") }

    NumField("Length (mm)", length) { length = it }
    NumField("Width (mm)", width) { width = it }
    NumField("Thickness (mm)", thickness) { thickness = it }
    NumField("Density (kg/m3, default steel)", density) { density = it }

    val l = length.toDoubleOrNull()
    val w = width.toDoubleOrNull()
    val t = thickness.toDoubleOrNull()
    val d = density.toDoubleOrNull()

    if (l != null && w != null && t != null && d != null && l > 0 && w > 0 && t > 0 && d > 0) {
        val weightKg = (l / 1000) * (w / 1000) * (t / 1000) * d
        val weightText = "Estimated Weight: " + String.format("%.2f", weightKg) + " kg"
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(weightText, fontWeight = FontWeight.Bold)
                Text("Formula: Weight = L(m) x W(m) x T(m) x Density", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (length.isNotEmpty() || width.isNotEmpty() || thickness.isNotEmpty()) {
        Text("Enter valid positive numbers for all fields.", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}
