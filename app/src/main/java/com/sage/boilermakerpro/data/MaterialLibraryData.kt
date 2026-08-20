package com.sage.boilermakerpro.data

data class MaterialItem(
    val name: String,
    val description: String,
    val applications: String,
    val considerations: String
)

val materialLibrary = listOf(
    MaterialItem(
        "Mild Steel",
        "Low carbon steel, widely used general fabrication steel.",
        "Structural work, general fabrication, brackets.",
        "Good weldability. Verify exact grade for critical applications."
    ),
    MaterialItem(
        "Carbon Steel",
        "Steel classified by carbon content affecting strength and hardness.",
        "Structural components, pressure applications.",
        "Weldability varies with carbon content; check grade or standard before use."
    ),
    MaterialItem(
        "Stainless Steel",
        "Corrosion-resistant steel alloy containing chromium.",
        "Food-grade, chemical, marine, hygienic applications.",
        "Requires specific welding consumables; verify grade such as 304 or 316."
    ),
    MaterialItem(
        "Aluminium",
        "Lightweight, corrosion-resistant non-ferrous metal.",
        "Lightweight structures, transport, marine.",
        "Requires TIG or MIG with correct filler; verify alloy grade."
    ),
    MaterialItem(
        "Galvanized Steel",
        "Steel coated with zinc for corrosion protection.",
        "Outdoor structures, roofing, ducting.",
        "Welding produces zinc fumes; ensure ventilation and coating removal at weld area."
    ),
    MaterialItem(
        "Structural Steel",
        "Steel sections designed for load-bearing structures.",
        "Beams, columns, structural frames.",
        "Verify grade or standard for design use."
    )
)
