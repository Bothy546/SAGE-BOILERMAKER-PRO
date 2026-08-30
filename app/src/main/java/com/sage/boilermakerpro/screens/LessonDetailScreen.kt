package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.sage.boilermakerpro.data.Lesson
import com.sage.boilermakerpro.data.completedLessons

@Composable
fun LessonDetailScreen(lesson: Lesson, onBack: () -> Unit) {
    var showQuiz by remember { mutableStateOf(false) }

    if (showQuiz) {
        QuizScreen(
            lesson = lesson,
            onFinish = {
                completedLessons[lesson.title] = true
                showQuiz = false
            },
            onBack = { showQuiz = false }
        )
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = onBack) { Text("Back") }
            }
            Text(lesson.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(lesson.level, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(12.dp))
            Text(lesson.content)
            Spacer(Modifier.height(20.dp))
            Button(onClick = { showQuiz = true }) {
                Text("Take Quiz")
            }
        }
    }
}
