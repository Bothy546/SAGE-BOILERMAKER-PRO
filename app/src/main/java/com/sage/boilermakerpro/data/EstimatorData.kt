package com.sage.boilermakerpro.data

import androidx.compose.runtime.mutableStateListOf

data class Estimate(
    var name: String,
    var materialCost: String = "",
    var materialQty: String = "",
    var labourHours: String = "",
    var labourRate: String = "",
    var consumablesCost: String = "",
    var transportCost: String = "",
    var otherCost: String = "",
    var markupPercent: String = ""
)

val savedEstimates = mutableStateListOf<Estimate>()

fun calculateTotal(estimate: Estimate): Double {
    val matCost = estimate.materialCost.toDoubleOrNull() ?: 0.0
    val matQty = estimate.materialQty.toDoubleOrNull() ?: 1.0
    val labHours = estimate.labourHours.toDoubleOrNull() ?: 0.0
    val labRate = estimate.labourRate.toDoubleOrNull() ?: 0.0
    val consumables = estimate.consumablesCost.toDoubleOrNull() ?: 0.0
    val transport = estimate.transportCost.toDoubleOrNull() ?: 0.0
    val other = estimate.otherCost.toDoubleOrNull() ?: 0.0
    val markup = estimate.markupPercent.toDoubleOrNull() ?: 0.0

    val materialTotal = matCost * matQty
    val labourTotal = labHours * labRate
    val subtotal = materialTotal + labourTotal + consumables + transport + other
    val markupAmount = subtotal * (markup / 100)
    return subtotal + markupAmount
}
