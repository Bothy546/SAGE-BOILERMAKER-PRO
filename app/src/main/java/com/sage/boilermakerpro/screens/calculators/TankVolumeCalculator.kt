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
import kotlin.math.PI

@Composable
fun TankVolumeCalculator() {
    var diameter by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    NumField("Tank Diameter (mm)", diameter) { diameter = it }
    NumField("Tank Height (mm)", height) { height = it }

    val d = diameter.toDoubleOrNull()
    val h = height.toDoubleOrNull()

    if (d != null && h != null && d > 0 && h > 0) {
        val radiusM = (d / 1000) / 2
        val heightM = h / 1000
        val volumeM3 = PI * radiusM * radiusM * heightM
        val volumeLitres = volumeM3 * 1000

        val volumeM3Text = "Volume: " + String.format("%.3f", volumeM3) + " cubic metres"
        val volumeLText = "Volume: " + String.format("%.1f", volumeLitres) + " litres"

        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(volumeM3Text, fontWeight = FontWeight.Bold)
                Text(volumeLText, fontWeight = FontWeight.Bold)
                Text("Formula: Volume = pi x radius squared x height", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (diameter.isNotEmpty() || height.isNotEmpty()) {
        Text("Enter valid positive numbers.")
    }
    DisclaimerNote()
}
