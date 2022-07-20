package de.cristiano.marathon

/**
 * Challenges from <a href="https://www.dailycodingproblem.com/">Daily Coding Problem</a>
 */
class DailyCodingProblem {

    /**
     * Given a string and a number of lines k, print the string in zigzag form. In zigzag,
     * characters are printed out diagonally from top left to bottom right until reaching the kth line,
     * then back up to top right, and so on.
     */
    fun zigzag() {


    }

    /**
     * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order,
     * return a sorted array of only the integers that appeared in all three arrays.
     *
     * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
     * Output: [1,5]
     * Explanation: Only 1 and 5 appeared in the three arrays.
     */
    fun arraysIntersection(a1: IntArray, a2: IntArray, a3: IntArray): IntArray {
        val result = IntArray(a1.size)

        var index1 = 0
        var index2 = 0
        var index3 = 0
        var indexR = 0

        while (index1 < a1.size && index2 < a2.size && index3 < a3.size) {
            when {
                a1[index1] == a2[index2] && a1[index1] == a3[index3] -> {
                    result[indexR] = a2[index2]

                    index1++
                    index2++
                    index3++
                    indexR++
                }

                a1[index1] < a2[index2] -> index1++
                a2[index2] < a3[index3] -> index2++
                a3[index3] < a1[index1] -> index3++
            }
        }

        return result
    }

    /**
     * Given a list of words, find all pairs of unique indices such that the concatenation of the two words is a
     * palindrome.
     */
    fun palindromePairs(words: Array<String>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        words.forEachIndexed { index, word ->
            words.forEachIndexed { index2, word2 ->
                if (index != index2 && istPalindrome(word + word2)) result.add(index to index2)
            }
        }

        return result
    }

    private fun istPalindrome(word: String): Boolean {
        var leftIndex = 0
        var rightIndex = word.lastIndex

        while (leftIndex < rightIndex) {
            if (word[rightIndex] != word[leftIndex]) return false

            rightIndex--
            leftIndex++
        }

        return true
    }

    /**
     * Given a string and a number of lines, print the string in zigzag form.
     */
    fun printZigZagFrom(string: String, depth: Int): String {
        val zigZag = Array(depth) { StringBuilder(" ".repeat(string.length)) }
        var depthIndex = 0
        var down = true

        string.forEachIndexed { index, char ->
            zigZag[depthIndex].insert(index, char)

            if (depthIndex == zigZag.lastIndex) down = false
            if (depthIndex == 0) down = true
            if (down) depthIndex++ else depthIndex--
        }

        return zigZag.joinToString(separator = "\n")
    }
}
