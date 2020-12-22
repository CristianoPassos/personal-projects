package de.cristiano.marathon

/**
 * [TOP 30 Java Interview Coding Tasks](https://books.google.de/books/about/TOP_30_Java_Interview_Coding_Tasks.html)
 */
class Top30JavaInterviewCodingTasks {

    fun exercise15(seed: String): Set<String> {
        val permutations = mutableSetOf<String>()

        permutations("", seed, permutations)

        return permutations
    }

    private fun permutations(prefix: String, seed: String, accumulator: MutableSet<String>) {
        if (seed.isBlank()) accumulator.add(prefix)

        seed.forEachIndexed { index, c ->
            permutations(prefix + c, seed.removeRange(index, index + 1), accumulator)
        }
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val complements = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, value ->
            val complement = target - index
            val complementIndex = complements[complement]

            if (complementIndex != null) return intArrayOf(complementIndex, index)
            else complements[value] = index

        }

        error("Invalid array")
    }


}
