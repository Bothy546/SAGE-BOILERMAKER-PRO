package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.toolLibrary
import com.sage.boilermakerpro.data.materialLibrary

@Composable
fun LibraryScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("Tools") })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("Materials") })
        }
        if (selectedTab == 0) {
            LazyColumn(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(toolLibrary) { tool ->
                    Card {
                        Column(Modifier.padding(12.dp)) {
                            Text(tool.name, fontWeight = FontWeight.Bold)
                            Text("Purpose: " + tool.purpose, style = MaterialTheme.typography.bodySmall)
                            Text("Safety: " + tool.safety, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        } else {
            LazyColumn(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(materialLibrary) { mat ->
                    Card {
                        Column(Modifier.padding(12.dp)) {
                            Text(mat.name, fontWeight = FontWeight.Bold)
                            Text(mat.description, style = MaterialTheme.typography.bodySmall)
                            Text("Applications: " + mat.applications, style = MaterialTheme.typography.bodySmall)
                            Text("Note: " + mat.considerations, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
