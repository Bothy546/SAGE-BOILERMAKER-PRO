package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class ChecklistItem(val label: String, var checked: Boolean = false)

@Composable
fun SafetyScreen() {
    val hotWorkChecklist = remember {
        mutableStateListOf(
            ChecklistItem("Hot work permit obtained"),
            ChecklistItem("Fire extinguisher on hand"),
            ChecklistItem("Area cleared of flammables"),
            ChecklistItem("Fire watch assigned"),
            ChecklistItem("PPE inspected and worn"),
            ChecklistItem("Ventilation confirmed adequate"),
            ChecklistItem("Gas cylinders secured and checked for leaks")
        )
    }

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("SAGE Safety Centre", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(
                "This checklist does not replace workplace procedures, permits, risk assessments or local regulations.",
                style = MaterialTheme.typography.bodySmall
            )
        }
        item { Text("Hot Work Checklist", fontWeight = FontWeight.Bold) }
        items(hotWorkChecklist) { checkItem ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checkItem.checked, onCheckedChange = { checkItem.checked = it })
                Text(checkItem.label)
            }
        }
    }
}
