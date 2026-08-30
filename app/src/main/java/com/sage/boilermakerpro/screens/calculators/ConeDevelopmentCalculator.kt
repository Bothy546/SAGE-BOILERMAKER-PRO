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
import kotlin.math.sqrt

@Composable
fun ConeDevelopmentCalculator() {
    var bottomDiameter by remember { mutableStateOf("") }
    var topDiameter by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    NumField("Bottom Diameter (mm)", bottomDiameter) { bottomDiameter = it }
    NumField("Top Diameter (mm)", topDiameter) { topDiameter = it }
    NumField("Vertical Height (mm)", height) { height = it }

    val d1 = bottomDiameter.toDoubleOrNull()
    val d2 = topDiameter.toDoubleOrNull()
    val h = height.toDoubleOrNull()

    if (d1 != null && d2 != null && h != null && d1 > 0 && d2 >= 0 && h > 0 && d1 > d2) {
        val r1 = d1 / 2
        val r2 = d2 / 2
        val slantHeight = sqrt(h * h + (r1 - r2) * (r1 - r2))
        val developedOuterRadius = (slantHeight * r1) / (r1 - r2)
        val developedInnerRadius = (slantHeight * r2) / (r1 - r2)
        val sweepAngleDeg = (r1 / developedOuterRadius) * 360.0

        val outerText = "Developed Outer Radius: " + String.format("%.2f", developedOuterRadius) + " mm"
        val innerText = "Developed Inner Radius: " + String.format("%.2f", developedInnerRadius) + " mm"
        val slantText = "Slant Height: " + String.format("%.2f", slantHeight) + " mm"
        val angleText = "Sweep Angle: " + String.format("%.2f", sweepAngleDeg) + " degrees"

        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text(slantText, fontWeight = FontWeight.Bold)
                Text(outerText, fontWeight = FontWeight.Bold)
                Text(innerText, fontWeight = FontWeight.Bold)
                Text(angleText, fontWeight = FontWeight.Bold)
                Text("Mark out the developed pattern using these radii and sweep angle on flat plate before rolling.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (bottomDiameter.isNotEmpty() || topDiameter.isNotEmpty() || height.isNotEmpty()) {
        Text("Enter valid values. Bottom diameter must be greater than top diameter.")
    }
    DisclaimerNote()
}
