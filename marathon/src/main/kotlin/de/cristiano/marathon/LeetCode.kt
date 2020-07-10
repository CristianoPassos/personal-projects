package de.cristiano.marathon

import de.cristiano.marathon.dcp.utils.Constants.EMPTY

fun numberOfSteps(num: Int): Int {
    return when {
        num == 0 -> 0
        num % 2 == 0 -> 1 + numberOfSteps(num / 2)
        else -> 1 + numberOfSteps(num - 1)
    }
}

fun uniqueMorseRepresentations(words: Array<String>): Int {
    val morse = arrayOf(
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-",
        "...-", ".--", "-..-", "-.--", "--.."
    )

    return words.map { it.toCharArray() }
        .map { word -> word.joinToString(separator = EMPTY) { char -> morse[char - 'a'] } }
        .toHashSet()
        .size
}
