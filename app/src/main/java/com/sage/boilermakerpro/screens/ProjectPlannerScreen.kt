package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Project
import com.sage.boilermakerpro.data.generateProjectFromProposal
import com.sage.boilermakerpro.data.savedProjects

@Composable
fun ProjectPlannerScreen() {
    var selectedProject by remember { mutableStateOf<Project?>(null) }
    var showNewProjectDialog by remember { mutableStateOf(false) }

    if (selectedProject != null) {
        ProjectDetailScreen(
            project = selectedProject!!,
            onBack = { selectedProject = null }
        )
    } else {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("SAGE Project Guide", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                IconButton(onClick = { showNewProjectDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = "New Project")
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "Type a short proposal of what you want to build. SAGE will generate a structured project template for you to complete.",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(12.dp))

            if (savedProjects.isEmpty()) {
                Text("No projects yet. Tap plus to start one.", style = MaterialTheme.typography.bodyMedium)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(savedProjects) { project ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { selectedProject = project }
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(project.name, fontWeight = FontWeight.Bold)
                                Text(
                                    project.steps.size.toString() + " steps generated",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showNewProjectDialog) {
        NewProposalDialog(
            onDismiss = { showNewProjectDialog = false },
            onCreate = { proposal ->
                val newProject = generateProjectFromProposal(proposal)
                savedProjects.add(newProject)
                showNewProjectDialog = false
                selectedProject = newProject
            }
        )
    }
}

@Composable
private fun NewProposalDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var proposal by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Project Proposal") },
        text = {
            OutlinedTextField(
                value = proposal,
                onValueChange = { proposal = it },
                label = { Text("Describe what you want to build") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(onClick = { if (proposal.isNotBlank()) onCreate(proposal) }) {
                Text("Generate")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
