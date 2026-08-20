package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import com.sage.boilermakerpro.data.Project
import com.sage.boilermakerpro.data.ProjectTask
import com.sage.boilermakerpro.data.TaskStatus
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
                Text("SAGE Project Planner", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                IconButton(onClick = { showNewProjectDialog = true }) {
                    Icon(Icons.Filled.Add, contentDescription = "New Project")
                }
            }
            Spacer(Modifier.height(12.dp))

            if (savedProjects.isEmpty()) {
                Text("No projects yet. Tap + to create one.", style = MaterialTheme.typography.bodyMedium)
            } else {
                LazyColum
