package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.completedLessons
import com.sage.boilermakerpro.data.lessons
import com.sage.boilermakerpro.data.quizScores
import com.sage.boilermakerpro.data.savedEstimates
import com.sage.boilermakerpro.data.savedProjects

data class ProfileStat(val label: String, val value: String)

@Composable
fun ProfileScreen() {
    val completedCount = completedLessons.values.count { it }
    val quizCount = quizScores.size
    val avgScore = if (quizScores.isNotEmpty()) {
        quizScores.values.sum().toDouble() / quizScores.size
    } else 0.0

    val stats = listOf(
        ProfileStat("Lessons Completed", completedCount.toString() + " of " + lessons.size),
        ProfileStat("Quizzes Taken", quizCount.toString()),
        ProfileStat("Average Quiz Score", String.format("%.1f", avgScore)),
        ProfileStat("Saved Projects", savedProjects.size.toString()),
        ProfileStat("Saved Estimates", savedEstimates.size.toString())
    )

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("SAGE Profile", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text("Track your training progress and saved work.", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(16.dp))

        stats.forEach { stat ->
            Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(
                    Modifier.padding(12.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stat.label)
                    Text(stat.value, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Recent Projects", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        if (savedProjects.isEmpty()) {
            Text("No projects saved yet.", style = MaterialTheme.typography.bodySmall)
        } else {
            savedProjects.take(3).forEach { project ->
                Text("- " + project.name, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
