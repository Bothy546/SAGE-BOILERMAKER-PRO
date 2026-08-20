package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI

@Composable
fun PipeCalculator() {
    var od by remember { mutableStateOf("") }
    var wt by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var density by remember { mutableStateOf("7850") }

    NumField("Outside Diameter (mm)", od) { od = it }
    NumField("Wall Thickness (mm)", wt) { wt = it }
    NumField("Length (mm)", length) { length = it }
    NumField("Density (kg/m3, default steel)", density) { density = it }

    val odV = od.toDoubleOrNull()
    val wtV = wt.toDoubleOrNull()
    val lV = length.toDoubleOrNull()
    val dV = density.toDoubleOrNull()

    if (odV != null && wtV != null && lV != null && dV != null && odV > 0 && wtV > 0 && lV > 0 && dV > 0 && wtV < odV / 2) {
        val idV = odV - 2 * wtV
        val areaMm2 = (PI / 4) * (odV * odV - idV * idV)
        val volumeM3 = (areaMm2 / 1_000_000) * (lV / 1000)
        val weightKg = volumeM3 * dV
        val circumferenceMm = PI * odV
        val weightText = "Estimated Weight: " + String.format("%.2f", weightKg) + " kg"
        val circText = "Circumference: " + String.format("%.2f", circumferenceMm) + " mm"
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(weightText, fontWeight = FontWeight.Bold)
                Text(circText, fontWeight = FontWeight.Bold)
                Text("Formula: Weight = Cross-section Area x Length x Density", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (od.isNotEmpty() || wt.isNotEmpty() || length.isNotEmpty()) {
        Text("Enter valid values (wall thickness must be less than OD/2).", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}
