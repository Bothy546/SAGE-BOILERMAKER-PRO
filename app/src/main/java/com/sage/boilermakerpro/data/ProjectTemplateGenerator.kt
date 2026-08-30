package com.sage.boilermakerpro.data

fun generateProjectFromProposal(proposal: String): Project {
    val lower = proposal.lowercase()

    val objective = "Design, fabricate and complete: " + proposal.trim()

    val materials = mutableListOf<String>()
    val tools = mutableListOf<String>()
    val safety = mutableListOf<String>()
    val quality = mutableListOf<String>()
    val steps = mutableListOf<String>()

    if (lower.contains("tank") || lower.contains("vessel")) {
        materials.add("Mild steel or stainless steel plate, appropriate grade for pressure or storage use")
        tools.add("Plate rolls, welding machine, angle grinder, measuring tape")
        safety.add("Confined space entry precautions if working inside the tank")
        quality.add("Check weld seams for leaks using dye penetrant or hydrostatic test")
        steps.add("Mark out and cut plate to developed dimensions")
        steps.add("Roll or form plate to required shape")
        steps.add("Tack weld and check alignment before final welding")
    }

    if (lower.contains("pipe") || lower.contains("piping")) {
        materials.add("Steel pipe of specified schedule and diameter")
        tools.add("Pipe cutter, welding machine, pipe wrenches, level")
        safety.add("Support pipe sections securely to prevent falling during fit-up")
        quality.add("Check pipe alignment and root gap before welding")
        steps.add("Cut pipe to length and prepare joints")
        steps.add("Fit up and tack weld pipe sections")
        steps.add("Complete root, fill and cap welding passes")
    }

    if (lower.contains("bracket") || lower.contains("frame") || lower.contains("structure") || lower.contains("structural")) {
        materials.add("Structural steel sections as per drawing (angle, channel, or plate)")
        tools.add("Angle grinder, welding machine, combination square, clamps")
        safety.add("Secure workpieces in clamps or vice before cutting or grinding")
        quality.add("Check squareness and dimensions against drawing before welding")
        steps.add("Cut sections to length per drawing")
        steps.add("Assemble and clamp components in position")
        steps.add("Tack weld, check alignment, then complete welding")
    }

    if (lower.contains("gate") || lower.contains("door") || lower.contains("railing") || lower.contains("balustrade")) {
        materials.add("Mild steel tube or angle, hinges, and fixing hardware")
        tools.add("Welding machine, angle grinder, tape measure, spirit level")
        safety.add("Support heavy sections while positioning to avoid crush injuries")
        quality.add("Check the item opens, closes, or functions smoothly before handover")
        steps.add("Measure opening and mark out material to size")
        steps.add("Cut and assemble frame components")
        steps.add("Weld frame, fit hinges or fixings, and test operation")
    }

    if (materials.isEmpty()) {
        materials.add("List the specific materials required for this project, including grade and dimensions")
    }
    if (tools.isEmpty()) {
        tools.add("List the tools and equipment required for this project")
    }
    if (safety.isEmpty()) {
        safety.add("Identify hazards specific to this task and required PPE")
    }
    if (quality.isEmpty()) {
        quality.add("Define how the finished work will be checked against the drawing or specification")
    }
    if (steps.isEmpty()) {
        steps.add("Plan and mark out the work")
        steps.add("Cut, fit and assemble components")
        steps.add("Weld or fasten and inspect finished work")
    }

    val newProject = Project(
        name = proposal.trim().take(60),
        objective = objective,
        scopeOfWork = "Describe in your own words what this project covers and does not cover.",
        materialsRequired = materials.joinToString("\n"),
        toolsRequired = tools.joinToString("\n"),
        safetyConsiderations = safety.joinToString("\n"),
        qualityControl = quality.joinToString("\n"),
        conclusion = "Summarise what was achieved, any challenges faced, and lessons learned."
    )

    steps.forEach { stepText ->
        newProject.steps.add(ProjectStep(text = stepText))
    }

    return newProject
}
