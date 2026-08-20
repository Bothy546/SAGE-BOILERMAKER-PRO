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

data class ChecklistItem(val label: String, val explanation: String, var checked: Boolean = false)

@Composable
fun SafetyScreen() {
    val hotWorkChecklist = remember {
        mutableStateListOf(
            ChecklistItem(
                "Hot work permit obtained",
                "A permit confirms the area has been assessed and approved for welding, cutting or grinding before work starts."
            ),
            ChecklistItem(
                "Fire extinguisher on hand",
                "Sparks and slag can travel several metres. A working extinguisher must be within reach at all times."
            ),
            ChecklistItem(
                "Area cleared of flammables",
                "Remove or shield combustible materials such as rags, dust, timber and solvents from the work zone."
            ),
            ChecklistItem(
                "Fire watch assigned",
                "A dedicated person should monitor for smouldering material during and after hot work, since fires can start after work stops."
            ),
            ChecklistItem(
                "PPE inspected and worn",
                "Check gloves, apron, helmet, and eye protection are undamaged and rated for the task before starting."
            ),
            ChecklistItem(
                "Ventilation confirmed adequate",
                "Fumes from welding and cutting can be harmful. Confirm airflow or extraction is working before you begin."
            ),
            ChecklistItem(
                "Gas cylinders secured and checked for leaks",
                "Upright, chained cylinders with no leaks reduce the risk of fire, explosion or toxic gas exposure."
            )
        )
    }

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
            Row(verticalAlignment = Alignment.Top) {
                Checkbox(checked = checkItem.checked, onCheckedChange = { checkItem.checked = it })
                Column(Modifier.padding(top = 12.dp)) {
                    Text(checkItem.label, fontWeight = FontWeight.Medium)
                    Text(
                        checkItem.explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
