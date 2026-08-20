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
    Out
