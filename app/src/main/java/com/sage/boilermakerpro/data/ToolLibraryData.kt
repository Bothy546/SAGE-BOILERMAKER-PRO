package com.sage.boilermakerpro.data

data class ToolItem(val name: String, val purpose: String, val safety: String)

val toolLibrary = listOf(
    ToolItem("Measuring Tape", "Measuring lengths and distances on the job.", "Retract carefully, watch for pinch points."),
    ToolItem("Vernier Caliper", "Precise measurement of thickness, diameter, depth.", "Handle with care, keep jaws clean."),
    ToolItem("Steel Rule", "Quick linear measurements and marking.", "Sharp edges, avoid cuts."),
    ToolItem("Try Square", "Checking and marking 90 degree angles.", "Keep reference edge clean and undamaged."),
    ToolItem("Combination Square", "Marking angles, depths, and levels.", "Lock clamp securely before use."),
    ToolItem("Spirit Level", "Checking horizontal and vertical alignment.", "Avoid dropping, check calibration."),
    ToolItem("Angle Grinder", "Cutting, grinding, and finishing metal.", "Guard must be fitted, wear face shield and gloves."),
    ToolItem("Welding Machine", "Joining metal via arc welding processes.", "Check leads and earths, wear full PPE."),
    ToolItem("Cutting Torch", "Oxy-fuel cutting of steel plate.", "Check hoses for leaks, use flashback arrestors."),
    ToolItem("Bench Vice", "Securing workpieces for filing, cutting, drilling.", "Ensure firmly mounted, keep fingers clear."),
    ToolItem("Clamps", "Holding workpieces during fabrication.", "Check load rating before use."),
    ToolItem("Files", "Manual removal of burrs and shaping metal.", "Use handle, never use without one."),
    ToolItem("Hammers", "Striking, forming, and shaping metal.", "Inspect handle and head before use."),
    ToolItem("Chipping Hammer", "Removing slag after welding.", "Wear eye protection at all times."),
    ToolItem("Drill", "Producing holes in metal and other materials.", "Secure workpiece, wear eye protection."),
    ToolItem("Magnetic Drill", "Drilling on vertical or overhead steel surfaces.", "Ensure magnet base is clean and fully seated."),
    ToolItem("Plasma Cutter", "Cutting electrically conductive metals.", "Wear appropriate shade lens, ensure ventilation."),
    ToolItem("Gas Cutting Equipment", "Oxy-fuel cutting and heating.", "Store cylinders upright, secure and chained.")
)
