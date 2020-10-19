package de.cristiano.marathon

import de.cristiano.marathon.dcp.utils.Constants

/**
 * Challenges from <a href="https://leetcode.com/">LeetCode</a>
 */
class LeetCode {
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
                .map { word -> word.joinToString(separator = Constants.EMPTY) { char -> morse[char - 'a'] } }
                .toHashSet()
                .size
    }

    /**
     * [Symmetric Tree](https://leetcode.com/problems/symmetric-tree)
     *
     */
    fun isSymmetric(root: TreeNode?) = if (root == null) true else checkSides(root.left, root.right)

    private fun checkSides(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        if (left.`val` != right.`val`) return false

        return checkSides(left.left, right.right) && checkSides(left.right, right.left)
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}