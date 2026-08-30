package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Lesson
import com.sage.boilermakerpro.data.completedLessons
import com.sage.boilermakerpro.data.lessons
import com.sage.boilermakerpro.data.quizScores

@Composable
fun AcademyScreen() {
    var selectedLesson by remember { mutableStateOf<Lesson?>(null) }

    if (selectedLesson != null) {
        LessonDetailScreen(
            lesson = selectedLesson!!,
            onBack = { selectedLesson = null }
        )
    } else {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("SAGE Boilermaking Academy", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            val completedCount = completedLessons.values.count { it }
            Text(
                completedCount.toString() + " of " + lessons.size + " lessons completed",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(lessons) { lesson ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { selectedLesson = lesson }
                    ) {
                        Row(
                            Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(lesson.title, fontWeight = FontWeight.Bold)
                                Text(lesson.level, style = MaterialTheme.typography.bodySmall)
                                val score = quizScores[lesson.title]
                                if (score != null) {
                                    Text(
                                        "Quiz score: " + score + " of " + lesson.quiz.size,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                            if (completedLessons[lesson.title] == true) {
                                Icon(Icons.Filled.CheckCircle, contentDescription = "Completed")
                            }
                        }
                    }
                }
            }
        }
    }
}
