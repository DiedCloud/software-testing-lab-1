package org.example

import kotlin.math.pow

tailrec fun factorialTailRecursive(n: Int, accumulator: Int = 1): Int {
    return if (n <= 1) {
        accumulator
    } else {
        factorialTailRecursive(n - 1, n * accumulator)
    }
}

fun cos(x: Double, n: Int = 5): Double =
    (0..n).map {
        (-1.0).pow(it) * x.pow(2 * it) /
                factorialTailRecursive(2 * it)
    }.sumOf { it }
