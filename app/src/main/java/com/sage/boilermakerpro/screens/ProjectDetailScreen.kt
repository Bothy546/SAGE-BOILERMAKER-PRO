package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun ProjectDetailScreen(project: Project, onBack: () -> Unit) {
    var objective by remember { mutableStateOf(project.objective) }
    var scopeOfWork by remember { mutableStateOf(project.scopeOfWork) }
    var materialsRequired by remember { mutableStateOf(project.materialsRequired) }
    var toolsRequired by remember { mutableStateOf(project.toolsRequired) }
    var safetyConsiderations by remember { mutableStateOf(project.safetyConsiderations) }
    var qualityControl by remember { mutableStateOf(project.qualityControl) }
    var conclusion by remember { mutableStateOf(project.conclusion) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("Back") }
        }
        Text(project.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        SectionLabel("Objective")
        OutlinedTextField(
            value = objective,
            onValueChange = { objective = it; project.objective = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SectionLabel("Scope of Work")
        OutlinedTextField(
            value = scopeOfWork,
            onValueChange = { scopeOfWork = it; project.scopeOfWork = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SectionLabel("Materials Required")
        OutlinedTextField(
            value = materialsRequired,
            onValueChange = { materialsRequired = it; project.materialsRequired = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SectionLabel("Tools Required")
        OutlinedTextField(
            value = toolsRequired,
            onValueChange = { toolsRequired = it; project.toolsRequired = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SectionLabel("Safety Considerations")
        OutlinedTextField(
            value = safetyConsiderations,
            onValueChange = { safetyConsiderations = it; project.safetyConsiderations = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SectionLabel("Quality Control")
        OutlinedTextField(
            value = qualityControl,
            onValueChange = { qualityControl = it; project.qualityControl = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        ProjectStepsSection(project)
        Spacer(Modifier.height(12.dp))

        SectionLabel("Conclusion")
        OutlinedTextField(
            value = conclusion,
            onValueChange = { conclusion = it; project.conclusion = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, fontWeight = FontWeight.Bold)
}
