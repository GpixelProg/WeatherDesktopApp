package utils

import kotlin.math.pow

fun Double.roundToDecimalPlaces(decimalPlaces: Int): Double {
    val scale = 10.0.pow(decimalPlaces.toDouble())
    return kotlin.math.round(this * scale) / scale
}