package com.sage.boilermakerpro.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI

private val calcOptions = listOf("Plate", "Pipe", "Angle", "Bend", "Unit Converter")

@Composable
fun CalculatorsScreen() {
    var selected by remember { mutableIntStateOf(0) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("SAGE Fabrication Calculators", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        ScrollableTabRow(selectedTabIndex = selected) {
            calcOptions.forEachIndexed { index, label ->
                Tab(selected = selected == index, onClick = { selected = index }, text = { Text(label) })
            }
        }
        Spacer(Modifier.height(12.dp))
        Column(Modifier.verticalScroll(rememberScrollState())) {
            when (selected) {
                0 -> PlateCalculator()
                1 -> PipeCalculator()
                2 -> AngleCalculator()
                3 -> BendCalculator()
                4 -> UnitConverter()
            }
        }
    }
}

@Composable
private fun NumField(label: String, value: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value, onValueChange = onChange, label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}

@Composable
private fun DisclaimerNote() {
    Text(
        "Estimate only. Verify critical dimensions against applicable engineering standards, drawings and manufacturer specifications.",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun PlateCalculator() {
    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var thickness by remember { mutableStateOf("") }
    var density by remember { mutableStateOf("7850") }

    NumField("Length (mm)", length) { length = it }
    NumField("Width (mm)", width) { width = it }
    NumField("Thickness (mm)", thickness) { thickness = it }
    NumField("Density (kg/m³, default steel)", density) { density = it }

    val l = length.toDoubleOrNull(); val w = width.toDoubleOrNull()
    val t = thickness.toDoubleOrNull(); val d = density.toDoubleOrNull()

    if (l != null && w != null && t != null && d != null && l > 0 && w > 0 && t > 0 && d > 0) {
        val weightKg = (l / 1000) * (w / 1000) * (t / 1000) * d
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("Estimated Weight: ${"%.2f".format(weightKg)} kg", fontWeight = FontWeight.Bold)
                Text("Formula: Weight = L(m) × W(m) × T(m) × Density", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (length.isNotEmpty() || width.isNotEmpty() || thickness.isNotEmpty()) {
        Text("Enter valid positive numbers for all fields.", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}

@Composable
private fun PipeCalculator() {
    var od by remember { mutableStateOf("") }
    var wt by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var density by remember { mutableStateOf("7850") }

    NumField("Outside Diameter (mm)", od) { od = it }
    NumField("Wall Thickness (mm)", wt) { wt = it }
    NumField("Length (mm)", length) { length = it }
    NumField("Density (kg/m³, default steel)", density) { density = it }

    val odV = od.toDoubleOrNull(); val wtV = wt.toDoubleOrNull()
    val lV = length.toDoubleOrNull(); val dV = density.toDoubleOrNull()

    if (odV != null && wtV != null && lV != null && dV != null && odV > 0 && wtV > 0 && lV > 0 && dV > 0 && wtV < odV / 2) {
        val idV = odV - 2 * wtV
        val areaMm2 = (PI / 4) * (odV * odV - idV * idV)
        val volumeM3 = (areaMm2 / 1_000_000) * (lV / 1000)
        val weightKg = volumeM3 * dV
        val circumferenceMm = PI * odV
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("Estimated Weight: ${"%.2f".format(weightKg)} kg", fontWeight = FontWeight.Bold)
                Text("Circumference: ${"%.2f".format(circumferenceMm)} mm", fontWeight = FontWeight.Bold)
                Text("Formula: Weight = Cross-section Area × Length × Density", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (od.isNotEmpty() || wt.isNotEmpty() || length.isNotEmpty()) {
        Text("Enter valid values (wall thickness must be less than OD/2).", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}

@Composable
private fun AngleCalculator() {
    var length by remember { mutableStateOf("") }
    var massPerMetre by remember { mutableStateOf("") }

    NumField("Length (m)", length) { length = it }
    NumField("Mass per metre (kg/m, from steel table)", massPerMetre) { massPerMetre = it }

    val l = length.toDoubleOrNull(); val m = massPerMetre.toDoubleOrNull()

    if (l != null && m != null && l > 0 && m > 0) {
        val totalWeight = l * m
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("Estimated Total Weight: ${"%.2f".format(totalWeight)} kg", fontWeight = FontWeight.Bold)
                Text("Formula: Total Weight = Length × Mass per Metre", style = MaterialTheme.typography.bodySmall)
                Text("Look up mass per metre for your angle size from a steel section table.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (length.isNotEmpty() || massPerMetre.isNotEmpty()) {
        Text("Enter valid positive numbers.", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}

@Composable
private fun BendCalculator() {
    var angle by remember { mutableStateOf("") }
    var thickness by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("") }
    var kFactor by remember { mutableStateOf("0.33") }

    NumField("Bend Angle (degrees)", angle) { angle = it }
    NumField("Material Thickness (mm)", thickness) { thickness = it }
    NumField("Inside Radius (mm)", radius) { radius = it }
    NumField("K-Factor (default 0.33)", kFactor) { kFactor = it }

    val a = angle.toDoubleOrNull(); val t = thickness.toDoubleOrNull()
    val r = radius.toDoubleOrNull(); val k = kFactor.toDoubleOrNull()

    if (a != null && t != null && r != null && k != null && a > 0 && a <= 180 && t > 0 && r >= 0 && k > 0) {
        val bendAllowance = (PI / 180) * a * (r + k * t)
        Card(Modifier.padding(top = 8.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("Bend Allowance: ${"%.2f".format(bendAllowance)} mm", fontWeight = FontWeight.Bold)
                Text("Formula: BA = (π/180) × Angle × (Radius + K × Thickness)", style = MaterialTheme.typography.bodySmall)
                Text("Add bend allowance to flat leg lengths to get developed length.", style = MaterialTheme.typography.bodySmall)
            }
        }
    } else if (angle.isNotEmpty() || thickness.isNotEmpty() || radius.isNotEmpty()) {
        Text("Enter valid values (angle between 0–180°).", color = MaterialTheme.colorScheme.error)
    }
    DisclaimerNote()
}

@Composable
private fun UnitConverter() {
    var value by remember { mutableStateOf("") }
    var fromUnit by remember { mutableStateOf("mm") }
    var toUnit by remember { mutableStateOf("cm") }

    val lengthUnitsToMm = mapOf("mm" to 1.0, "cm" to 10.0, "m" to 1000.0, "in" to 25.4, "ft" to 304.8)
    val massUnitsToKg = mapOf("kg" to 1.0, "t" to 1000.0)
    val allUnits = lengthUnitsToMm.keys + massUnitsToKg.keys

    NumField("Value", value) { value = it }

    Text("From:", style = MaterialTheme.typography.bodySmall)
    Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        allUnits.forEach { unit ->
            FilterChip(selected = fromUnit == unit, onClick = { fromUnit
