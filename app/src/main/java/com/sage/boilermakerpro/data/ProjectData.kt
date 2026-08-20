package com.sage.boilermakerpro.data

import androidx.compose.runtime.mutableStateListOf

enum class TaskStatus { NOT_STARTED, IN_PROGRESS, COMPLETED }

data class ProjectTask(
    val name: String,
    var status: TaskStatus = TaskStatus.NOT_STARTED
)

data class Project(
    var name: String,
    var reference: String = "",
    var dimensions: String = "",
    var material: String = "",
    var notes: String = "",
    val tasks: MutableList<ProjectTask> = mutableStateListOf()
)

val savedProjects = mutableStateListOf<Project>()
