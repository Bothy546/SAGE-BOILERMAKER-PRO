package com.sage.boilermakerpro.data

import androidx.compose.runtime.mutableStateListOf

data class ProjectStep(val text: String)

data class Project(
    var name: String,
    var objective: String = "",
    var scopeOfWork: String = "",
    var materialsRequired: String = "",
    var toolsRequired: String = "",
    var safetyConsiderations: String = "",
    var qualityControl: String = "",
    var conclusion: String = "",
    val steps: MutableList<ProjectStep> = mutableStateListOf()
)

val savedProjects = mutableStateListOf<Project>()
