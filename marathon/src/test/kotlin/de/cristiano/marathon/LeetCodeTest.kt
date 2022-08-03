package de.cristiano.marathon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class LeetCodeTest {

    private val leetCode = LeetCode()

    @Test
    fun numberOfStepsTest() {
        assertThat(leetCode.numberOfSteps(14)).isEqualTo(6)
    }

    @Test
    fun uniqueMorseRepresentationsTest() {
        assertThat(leetCode.uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg"))).isEqualTo(2)
    }


    @Test
    fun isSymmetricTest() {
        assertThat(leetCode.isSymmetric(null)).isTrue()
    }

    @Test
    fun mostCommonWord() {
        assertThat(leetCode.mostCommonWord("Bob. hIt, baLl", arrayOf("bob", "hit"))).isEqualTo("ball")

        assertThat(
            leetCode.mostCommonWord(
                "Bob hit a ball, the hit BALL flew far after it was hit.", arrayOf("hit")
            )
        ).isEqualTo("ball")
    }

    @Test
    fun validParentheses() {
        assertThat(leetCode.isValid("([)]")).isFalse()
    }

    @Test
    fun backspaceCompare() {
        assertThat(leetCode.backspaceCompare("ab##", "c#d#")).isTrue()
    }

    @Test
    fun bubbleSort() {
        val array = intArrayOf(2, 67, 3, 4, 8, 6, 45, 1, 2, 3, 4, 5, 98)
        leetCode.bubbleSort(array)
        assertThat(array).containsExactly(1, 2, 2, 3, 3, 4, 4, 5, 6, 8, 45, 67, 98)
    }

    @Test
    fun maxTwoDigitNumberTest() {
        assertThat(leetCode.maxTwoDigitNumber("50552")).isEqualTo(55)
        assertThat(leetCode.maxTwoDigitNumber("10101")).isEqualTo(10)
        assertThat(leetCode.maxTwoDigitNumber("88")).isEqualTo(88)
    }


    @Test
    fun minTimeToVisitAllPointsTest() {
        assertThat(
            leetCode.minTimeToVisitAllPoints(
                arrayOf(
                    intArrayOf(3, 2), intArrayOf(-2, 2)
                )
            )
        ).isEqualTo(5)

        assertThat(
            leetCode.minTimeToVisitAllPoints(
                arrayOf(
                    intArrayOf(1, 1), intArrayOf(3, 4), intArrayOf(-1, 0)
                )
            )
        ).isEqualTo(7)
    }

    @Test
    fun findDisappearedNumbersTest() {
        // Given
        val nums = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)

        // When
        val disappearedNumbers = leetCode.findDisappearedNumbers(nums)

        // Then
        assertThat(disappearedNumbers).contains(5, 6)
    }

    @Test
    fun flipGameTest() {
        // Given
        val game = "++++"

        // When
        val states = leetCode.flipGame(game)

        // Then
        assertThat(states).contains("--++", "+--+", "++--")
    }

    @Test
    fun flipAndInvertImageTest() {
        // Given
        val image = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 0, 1), intArrayOf(0, 0, 0))

        // When
        val flippedAndInvertedImage = leetCode.flipAndInvertImage(image)

        // Then
        assertThat(flippedAndInvertedImage).isDeepEqualTo(
            arrayOf(
                intArrayOf(1, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)
            )
        )
    }


    @Test
    fun isHappyTest() {
        // When
        val happy = leetCode.isHappy(19)

        // Then
        assertThat(happy).isTrue()
    }

    @Test
    fun licenseKeyFormattingTest() {
        val result1 = leetCode.licenseKeyFormatting("5F3Z-2e-9-w", 4)
        val result2 = leetCode.licenseKeyFormatting("2-5g-3-J", 2)
        val result3 = leetCode.licenseKeyFormatting("---", 2)

        assertThat(result1).isEqualTo("5F3Z-2E9W")
        assertThat(result2).isEqualTo("2-5G-3J")
        assertThat(result3).isEqualTo("")
    }

    @Test
    fun firstBadVersionTest() {
        val badVersion = leetCode.firstBadVersion(2126753390)

        assertThat(badVersion).isEqualTo(1702766720)
    }

    @Test
    fun searchInsertTest() {
        val test1 = leetCode.searchInsert(intArrayOf(1, 2, 3, 5, 6), 7)
        val test2 = leetCode.searchInsert(intArrayOf(2, 3, 5, 6), 1)
        val test3 = leetCode.searchInsert(intArrayOf(1, 2, 3, 4, 5, 6), 3)

        assertThat(test1).isEqualTo(5)
        assertThat(test2).isEqualTo(0)
        assertThat(test3).isEqualTo(2)
    }

    @Test
    fun sortedSquaresTest() {
        val test1 = leetCode.sortedSquares(intArrayOf(-4, -1, 0, 3, 10))
        val test2 = leetCode.sortedSquares(intArrayOf(-7, -3, 2, 3, 11))

        assertThat(test1).isEqualTo(intArrayOf(0, 1, 9, 16, 100))
        assertThat(test2).isEqualTo(intArrayOf(4, 9, 9, 49, 121))
    }

    @Test
    fun rotateTest() {
        // Given
        val test = intArrayOf(1, 2, 3, 4)
        val test1 = intArrayOf(-4, -1, 0, 3, 10)
        val test2 = intArrayOf(-7, -3, 3, 11)

        // When
        leetCode.rotate(test, 1)
        leetCode.rotate(test1, 2)
        leetCode.rotate(test2, 3)

        // Then
        assertThat(test).isEqualTo(intArrayOf(4, 1, 2, 3))
        assertThat(test1).isEqualTo(intArrayOf(3, 10, -4, -1, 0))
        assertThat(test2).isEqualTo(intArrayOf(-3, 3, 11, -7))
    }

    @Test
    fun moveZeroesTest() {
        // Given
        val test = intArrayOf(1, 0, 0, 4, 3)

        // When
        leetCode.moveZeroes(test)

        // Then
        assertThat(test).isEqualTo(intArrayOf(1, 4, 3, 0, 0))
    }

    @Test
    fun twoSumTest() {
        val result = leetCode.twoSum(intArrayOf(1, 0, 0, 4, 3), 7)

        // Then
        assertThat(result).isEqualTo(intArrayOf(3, 4))
    }

    @Test
    fun reverseWordsTest() {
        val result = leetCode.reverseWords("Dog Cat")

        assertThat(result).isEqualTo("goD taC")
    }

    @Test
    fun middleNodeTest() {
        val result = leetCode.middleNode(ListNode(1, ListNode(2, ListNode(3))))

        assertThat(result.`val`).isEqualTo(2)
    }

    @Test
    fun removeNthFromEndTest() {
        val result = leetCode.removeNthFromEnd(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 2)
        val result2 = leetCode.removeNthFromEnd(ListNode(1, ListNode(2)), 1)
        val result3 = leetCode.removeNthFromEnd(ListNode(1), 1)
        val result4 = leetCode.removeNthFromEnd(ListNode(1, ListNode(2)), 2)

        assertThat(result).isNotNull
        assertThat(result!!.toList()).containsExactly(1, 2, 3, 5)

        assertThat(result2).isNotNull
        assertThat(result2!!.toList()).containsExactly(1)

        assertThat(result3).isNull()

        assertThat(result4).isNotNull
        assertThat(result4!!.toList()).containsExactly(2)
    }

    @Test
    fun lengthOfLongestSubstringTest() {
        val result = leetCode.lengthOfLongestSubstring("abcabcbb")
        val result2 = leetCode.lengthOfLongestSubstring("pwwkew")
        val result3 = leetCode.lengthOfLongestSubstring("dvdf")

        assertThat(result).isEqualTo(3)
        assertThat(result2).isEqualTo(3)
        assertThat(result3).isEqualTo(3)
    }

    @Test
    fun checkInclusionTest() {
        val result = leetCode.checkInclusion("ab", "eidbaooo")
        val result2 = leetCode.checkInclusion("ab", "eidboaoo")
        val result3 = leetCode.checkInclusion("adc", "dcda")

        assertThat(result).isTrue()
        assertThat(result2).isFalse()
        assertThat(result3).isTrue()
    }

    @Test
    fun findAnagramsTest() {
        val result = leetCode.findAnagrams("cbaebabacd", "abc")
        val result2 = leetCode.findAnagrams("abab", "ab")

        assertThat(result).contains(0, 6)
        assertThat(result2).contains(0, 1, 2)
    }

    @Test
    fun updateMatrixTest() {
        val result = leetCode.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)))

        assertThat(result).isDeepEqualTo(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 2, 1)))
    }

    @Test
    fun orangesRottingTest() {
        val result = leetCode.orangesRotting(arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 1, 0), intArrayOf(1, 2, 1)))
        val result2 = leetCode.orangesRotting(arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 1, 0), intArrayOf(1, 0, 1)))

        assertThat(result).isEqualTo(2)
        assertThat(result2).isEqualTo(-1)
    }

    @Test
    fun reverseListTest() {
        val result = leetCode.reverseList(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))))
        val result2 = leetCode.reverseList(ListNode(1))

        assertThat(result?.toString()).isEqualTo("5 -> 4 -> 3 -> 2 -> 1")
        assertThat(result2?.toString()).isEqualTo("1")
    }

    @Test
    fun reverseList2Test() {
        val result = leetCode.reverseList2(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))))

        assertThat(result?.toString()).isEqualTo("5 -> 4 -> 3 -> 2 -> 1")
    }

    @Test
    fun matrixReshapeTest() {
        leetCode.matrixReshape(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 4, 1)
    }

    @Test
    fun pascalTriangleTest() {
        leetCode.generate(5)
    }

    @Test
    fun connectTest() {
        leetCode.connect(Node(1, Node(2, Node(3), Node(4)), Node(5, Node(6), Node(7))))
    }

    @Test
    fun pivotIndexTest() {
        leetCode.pivotIndex(intArrayOf(-1, -1, -1, -1, -1, 0))
    }

    @Test
    @Disabled
    fun searchRangeTest() {
        val result = leetCode.searchRange(intArrayOf(2), 1)

        assertThat(result).containsExactly(0, 0)
    }

    @Test
    fun isPalindromeTest() {
        leetCode.isPalindrome(ListNode(1, ListNode(2)))
    }

    @Test
    fun numArrayTest() {
        val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))

        numArray.sumRange(2, 5)
    }

    @Test
    @Disabled
    fun ladderLengthTest() {
        val result = leetCode.ladderLength("cet", "ism", mutableListOf("kid", "tag", "pup", "ail", "tun"))

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun verticalTraversalTest() {
        val result = leetCode.verticalTraversal(TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7))))
    }

    @Test
    fun subarraySumTest() {
        val result = leetCode.subarraySum(intArrayOf(1, 1, 1), 2)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun wordBreakTest() {
        val result = leetCode.wordBreak("leetcode", listOf("leet", "code"))

        assertThat(result).isTrue()
    }

    @Test
    fun gameOfLifeTest() {
        leetCode.gameOfLife(
            arrayOf(
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(0, 0, 0)
            )
        )
    }
}