package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI

@Composable
fun BendCalculator() {
    var angle by remember { mutableStateOf("") }
    var thickness by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("") }
    var kFactor by remember { mutableStateOf("0.33") }

    NumField("Bend Angle (degrees)", angle) { angle = it }
    NumField("Material Thickness (mm)", thickness) { thickness = it }
    NumField("Inside Radius (mm)", radius) { radius = it }
    NumField("K-Factor (default 0.33)", kFactor) { kFactor = it }

    val a = angle.toDoubleOrNull()
    val t = thickness.toDoubleOrNull()
    val r = radius.toDoubleOrNull()
    val k = kFactor.toDoubleOrNull()

    if (a != null && t != null && r != null && k != null && a > 0 && a <= 180 && t > 0 && r >= 0 && k > 0) {
        val bendAllowance = (PI / 180) * a * (r + k * t)
        val bendText = "Bend Allowance: " + String.format("%.2f", bendAllowance) + " mm"
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(bendText, fontWeight = FontWeight.Bold)
                Text("Formula: BA = (pi/180) x Angle x (Radius + K x Thickness)", style = MaterialTheme.typography.bodySmall)
                Text("Add bend allowance to flat leg lengths to get developed length.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (angle.isNotEmpty() || thickness.isNotEmpty() || radius.isNotEmpty()) {
        Text("Enter valid values (angle between 0-180 degrees).", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}
