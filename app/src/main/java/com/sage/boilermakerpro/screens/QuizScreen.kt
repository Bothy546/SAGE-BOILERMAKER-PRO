package com.sage.boilermakerpro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sage.boilermakerpro.data.Lesson
import com.sage.boilermakerpro.data.quizScores

@Composable
fun QuizScreen(lesson: Lesson, onFinish: () -> Unit, onBack: () -> Unit) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    if (showResult) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("Quiz Complete", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Score: " + score + " of " + lesson.quiz.size)
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                quizScores[lesson.title] = score
                onFinish()
            }) {
                Text("Finish")
            }
        }
    } else {
        val question = lesson.quiz[currentIndex]
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = onBack) { Text("Back") }
            }
            Text(
                "Question " + (currentIndex + 1) + " of " + lesson.quiz.size,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(8.dp))
            Text(question.question, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))

            question.options.forEachIndexed { index, option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedOption == index,
                            onClick = { selectedOption = index }
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedOption == index, onClick = { selectedOption = index })
                    Text(option)
                }
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    if (selectedOption == question.correctIndex) {
                        score = score + 1
                    }
                    selectedOption = -1
                    if (currentIndex < lesson.quiz.size - 1) {
                        currentIndex = currentIndex + 1
                    } else {
                        showResult = true
                    }
                },
                enabled = selectedOption != -1
            ) {
                Text(if (currentIndex < lesson.quiz.size - 1) "Next" else "Finish Quiz")
            }
        }
    }
}
