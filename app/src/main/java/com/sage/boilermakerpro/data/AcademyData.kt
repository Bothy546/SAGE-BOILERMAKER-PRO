package com.sage.boilermakerpro.data

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)

data class Lesson(
    val title: String,
    val level: String,
    val content: String,
    val quiz: List<QuizQuestion>
)

val lessons = listOf(
    Lesson(
        title = "Introduction to Boilermaking",
        level = "Beginner",
        content = "Boilermaking is a trade involving the fabrication, assembly, and repair of boilers, tanks, pressure vessels and structural steel. Boilermakers work with plate, pipe, and structural sections, using cutting, welding, and forming processes to build and maintain equipment used across industries such as mining, power generation, and manufacturing.",
        quiz = listOf(
            QuizQuestion(
                question = "What is a core skill area for boilermakers?",
                options = listOf("Fabrication and welding", "Software coding", "Accounting", "Landscaping"),
                correctIndex = 0
            ),
            QuizQuestion(
                question = "Which industries commonly employ boilermakers?",
                options = listOf("Mining and power generation", "Retail fashion", "Film production", "Culinary arts"),
                correctIndex = 0
            )
        )
    ),
    Lesson(
        title = "Workshop Safety Basics",
        level = "Beginner",
        content = "Safety is the foundation of boilermaking work. Before starting any task, always inspect your PPE, check tools and equipment for damage, and be aware of hazards in your work area such as sparks, fumes, and moving machinery. Following safe work procedures reduces the risk of injury to yourself and others.",
        quiz = listOf(
            QuizQuestion(
                question = "What should you do before starting any task?",
                options = listOf("Inspect PPE and equipment", "Skip straight to work", "Ignore hazards", "Remove PPE"),
                correctIndex = 0
            )
        )
    ),
    Lesson(
        title = "Measurement and Marking Out",
        level = "Beginner",
        content = "Accurate measurement and marking out are essential before cutting or fabricating any component. Common tools include the steel rule, tape measure, combination square, and scriber. Always measure twice before cutting, and double check reference points against the drawing.",
        quiz = listOf(
            QuizQuestion(
                question = "Why should you measure twice before cutting?",
                options = listOf("To avoid costly mistakes", "It is not necessary", "To waste time", "To confuse others"),
                correctIndex = 0
            )
        )
    ),
    Lesson(
        title = "Welding Processes Overview",
        level = "Intermediate",
        content = "Common welding processes in boilermaking include SMAW (stick), GMAW (MIG), GTAW (TIG), and FCAW (flux-cored). Each process has different applications depending on material type, thickness, and required finish quality. Settings should always come from a Welding Procedure Specification (WPS) or qualified supervision.",
        quiz = listOf(
            QuizQuestion(
                question = "Where should welding settings come from?",
                options = listOf("A WPS or qualified supervision", "Guessing", "Any online video", "The weather"),
                correctIndex = 0
            )
        )
    ),
    Lesson(
        title = "Pipe Fabrication Basics",
        level = "Intermediate",
        content = "Pipe fabrication involves cutting, fitting, and welding pipe sections to specified dimensions and angles. Key considerations include pipe schedule, wall thickness, and proper joint preparation to ensure structural integrity and weld quality.",
        quiz = listOf(
            QuizQuestion(
                question = "What is an important consideration in pipe fabrication?",
                options = listOf("Joint preparation", "Ignoring wall thickness", "Random cutting", "Skipping fit-up"),
                correctIndex = 0
            )
        )
    ),
    Lesson(
        title = "Introduction to Pressure Vessels",
        level = "Advanced",
        content = "Pressure vessels are containers designed to hold gases or liquids at a pressure different from ambient pressure. Their design and fabrication must follow strict codes and standards due to the safety risks involved. Boilermakers working on pressure vessels require additional qualification and must follow documented procedures closely.",
        quiz = listOf(
            QuizQuestion(
                question = "Why do pressure vessels require strict codes and standards?",
                options = listOf("Because of safety risks", "They do not need any standards", "For decoration purposes", "To increase cost only"),
                correctIndex = 0
            )
        )
    )
)
