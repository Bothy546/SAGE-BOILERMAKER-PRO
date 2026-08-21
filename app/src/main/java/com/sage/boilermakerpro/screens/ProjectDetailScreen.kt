package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Project
import com.sage.boilermakerpro.data.ProjectTask
import com.sage.boilermakerpro.data.TaskStatus

@Composable
fun ProjectDetailScreen(project: Project, onBack: () -> Unit) {
    var reference by remember { mutableStateOf(project.reference) }
    var dimensions by remember { mutableStateOf(project.dimensions) }
    var material by remember { mutableStateOf(project.material) }
    var notes by remember { mutableStateOf(project.notes) }
    var newTaskName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("Back") }
        }
        Text(project.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = reference,
            onValueChange = { reference = it; project.reference = it },
            label = { Text("Reference") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = dimensions,
            onValueChange = { dimensions = it; project.dimensions = it },
            label = { Text("Dimensions") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = material,
            onValueChange = { material = it; project.material = it },
            label = { Text("Material") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it; project.notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text("Tasks", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))

        project.tasks.forEach { task ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(task.name, modifier = Modifier.weight(1f))
                TaskStatusDropdown(task)
                IconButton(onClick = { project.tasks.remove(task) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete task")
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newTaskName,
                onValueChange = { newTaskName = it },
                label = { Text("New task") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                if (newTaskName.isNotBlank()) {
                    project.tasks.add(ProjectTask(name = newTaskName))
                    newTaskName = ""
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add task")
            }
        }
    }
}

@Composable
private fun TaskStatusDropdown(task: ProjectTask) {
    var expanded by remember { mutableStateOf(false) }
    val label = when (task.status) {
        TaskStatus.NOT_STARTED -> "Not started"
        TaskStatus.IN_PROGRESS -> "In progress"
        TaskStatus.COMPLETED -> "Completed"
    }
    Box {
        TextButton(onClick = { expanded = true }) { Text(label) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Not started") }, onClick = { task.status = TaskStatus.NOT_STARTED; expanded = false })
            DropdownMenuItem(text = { Text("In progress") }, onClick = { task.status = TaskStatus.IN_PROGRESS; expanded = false })
            DropdownMenuItem(text = { Text("Completed") }, onClick = { task.status = TaskStatus.COMPLETED; expanded = false })
        }
    }
}
