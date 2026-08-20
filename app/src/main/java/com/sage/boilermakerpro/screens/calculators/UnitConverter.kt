package com.sage.boilermakerpro.screens.calculators

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UnitConverter() {
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
            FilterChip(selected = fromUnit == unit, onClick = { fromUnit = unit }, label = { Text(unit) })
        }
    }
    Spacer(Modifier.height(8.dp))
    Text("To:", style = MaterialTheme.typography.bodySmall)
    Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        allUnits.forEach { unit ->
            FilterChip(selected = toUnit == unit, onClick = { toUnit = unit }, label = { Text(unit) })
        }
    }

    val v = value.toDoubleOrNull()
    if (v != null) {
        val result: Double? = when {
            lengthUnitsToMm.containsKey(fromUnit) && lengthUnitsToMm.containsKey(toUnit) ->
                v * lengthUnitsToMm.getValue(fromUnit) / lengthUnitsToMm.getValue(toUnit)
            massUnitsToKg.containsKey(fromUnit) && massUnitsToKg.containsKey(toUnit) ->
                v * massUnitsToKg.getValue(fromUnit) / massUnitsToKg.getValue(toUnit)
            else -> null
        }
        Card(Modifier.padding(top = 12.dp).fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                if (result != null) {
                    val resultText = "Result: " + String.format("%.4f", result) + " " + toUnit
                    Text(resultText, fontWeight = FontWeight.Bold)
                } else {
                    Text("Cannot convert between length and mass units.", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
