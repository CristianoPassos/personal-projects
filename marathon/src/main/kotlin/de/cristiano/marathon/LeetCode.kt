package de.cristiano.marathon

import de.cristiano.marathon.dcp.utils.Constants
import org.apache.commons.lang3.CharSequenceUtils.subSequence
import org.apache.commons.lang3.StringUtils.startsWith
import org.apache.commons.lang3.mutable.Mutable
import java.util.ArrayDeque
import java.util.Collections
import java.util.PriorityQueue
import java.util.Stack
import java.util.TreeMap
import kotlin.Int.Companion.MIN_VALUE
import kotlin.math.abs
import kotlin.math.max


/**
 * Challenges from <a href="https://leetcode.com/">LeetCode</a>
 */
class LeetCode {

    fun numPairsDivisibleBy60(time: IntArray): Int {
        var numPairsDivisibleBy60 = 0
        val remainders = IntArray(60)

        time.forEach {
            val remainder = it % 60

            if (remainder == 0) numPairsDivisibleBy60 += remainders[remainder]
            else numPairsDivisibleBy60 += remainders[60 - remainder]

            remainders[remainder]++
        }

        return numPairsDivisibleBy60
    }

    fun killProcess(pid: List<Int>, ppid: List<Int>, kill: Int): List<Int> {
        val result = mutableListOf<Int>()

        val processById = mutableMapOf<Int, MutableList<Int>>()

        pid.forEachIndexed { index, pId ->
            val ppId = ppid[index]
            val pProcess = processById.getOrPut(ppId) { mutableListOf() }
            pProcess.add(pId)

            processById.putIfAbsent(pId, mutableListOf())
        }

        val list = mutableListOf(kill)

        while (list.isNotEmpty()) {
            val toKill = list.removeAt(0)

            processById[toKill]?.also { list.addAll(it) }

            result.add(toKill)
        }

        return result
    }

    fun knows(a: Int, b: Int): Boolean = false

    fun findCelebrity(n: Int): Int {
        var possibleCelebrity = 0

        for (i in 1 until n) {
            if (knows(possibleCelebrity, i)) possibleCelebrity = i
        }

        for (j in 0 until n) {
            if (j != possibleCelebrity && (knows(possibleCelebrity, j) || knows(j, possibleCelebrity).not())) {
                return -1
            }
        }

        return possibleCelebrity
    }

    fun find132pattern(nums: IntArray): Boolean {
        val iValues = IntArray(nums.size)

        iValues[0] = nums[0]

        for (index in 1..nums.lastIndex) {
            iValues[index] = nums[index].coerceAtMost(iValues[index - 1])
        }

        val kValues = Stack<Int>()

        for (j in nums.lastIndex downTo 0) {
            while (kValues.isNotEmpty() && kValues.peek() < nums[j]) {
                if (kValues.peek() > iValues[j]) return true

                kValues.pop()
            }

            kValues.push(nums[j])
        }

        return false
    }

    fun gameOfLife(board: Array<IntArray>) {
        val increments = intArrayOf(0, 1, -1)

        board.forEachIndexed { rowIndex, column ->
            column.forEachIndexed { columnIndex, element ->
                var liveNeighbors = 0

                increments.forEach { rowIncrement ->
                    increments.forEach { columnIncrement ->
                        if (rowIncrement == 0 && columnIncrement == 0) return@forEach

                        val rowToVisit = rowIndex + rowIncrement
                        val columnToVisit = columnIndex + columnIncrement

                        if (rowToVisit in board.indices &&
                            columnToVisit in column.indices &&
                            (board[rowToVisit][columnToVisit] == 1 || board[rowToVisit][columnToVisit] == -1)
                        ) liveNeighbors++
                    }
                }


                if (liveNeighbors < 2 && element == 1) board[rowIndex][columnIndex] = -1
                if (liveNeighbors in 2..3) board[rowIndex][columnIndex] = board[rowIndex][columnIndex]
                if (liveNeighbors > 3 && element == 1) board[rowIndex][columnIndex] = -1
                if (liveNeighbors == 3 && element == 0) board[rowIndex][columnIndex] = 2
            }
        }

        board.forEachIndexed { rowIndex, column ->
            column.forEachIndexed { columnIndex, element ->
                if (element == 2) board[rowIndex][columnIndex] = 1
                if (element == -1) board[rowIndex][columnIndex] = 0
            }
        }
    }

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordDictSet = wordDict.toSet()
        val subProblems = BooleanArray(s.length + 1)
        subProblems[0] = true

        for (startIndex in s.indices) {
            for (endIndex in startIndex + 1..s.length) {
                if (subProblems[startIndex]
                    && wordDictSet.contains(s.substring(startIndex, endIndex))
                ) {
                    if (endIndex == s.length) return true

                    subProblems[endIndex] = true
                }
            }
        }

        return subProblems[s.length]
    }

    fun numberOfSteps(num: Int): Int {
        return when {
            num == 0 -> 0
            num % 2 == 0 -> 1 + numberOfSteps(num / 2)
            else -> 1 + numberOfSteps(num - 1)
        }
    }

    fun reverseOnlyLetters(s: String): String {
        val reversedString = s.toCharArray()
        var lowerIndex = 0
        var upperIndex = s.lastIndex

        while (lowerIndex < upperIndex) {
            if (s[lowerIndex].isLetter() && s[upperIndex].isLetter()) {
                reversedString[upperIndex] =
                    reversedString[lowerIndex].also { reversedString[lowerIndex] = reversedString[upperIndex] }

                lowerIndex++
                upperIndex--
            }

            if (s[lowerIndex].isLetter().not()) lowerIndex++
            if (s[upperIndex].isLetter().not()) upperIndex--
        }

        return String(reversedString)
    }


    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val morse = arrayOf(
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
        )

        return words.map { it.toCharArray() }
            .map { word -> word.joinToString(separator = Constants.EMPTY) { char -> morse[char - 'a'] } }
            .toHashSet().size
    }

    fun maxTwoDigitNumber(s: String): Int {
        var max = 0

        for (i in 0 until s.lastIndex) {
            val newNumber = s.substring(i, i + 2).toInt()

            if (newNumber > max) max = newNumber
        }

        return max
    }

    fun uniqueNumberOfTimes(s: String): Int {
        var deletions = 0
        val distribution = hashMapOf<Char, Int>()

        s.map { distribution[it] = distribution.getOrDefault(it, 0) + 1 }

        for (i in 1..distribution.values.max()!!) {
            distribution.entries.count { it.value == i }
        }

        return deletions
    }

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        floodFill(image, sr, sc, image[sr][sc], newColor)

        return image
    }

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, oldColor: Int, newColor: Int) {
        if (sr !in image.indices || sc !in image[sr].indices || image[sr][sc] != oldColor || image[sr][sc] == newColor) return

        image[sr][sc] = newColor

        floodFill(image, sr + 1, sc, oldColor, newColor)
        floodFill(image, sr - 1, sc, oldColor, newColor)
        floodFill(image, sr, sc - 1, oldColor, newColor)
        floodFill(image, sr, sc + 1, oldColor, newColor)
    }

    fun lastStoneWeight(stones: IntArray): Int {
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

        stones.forEach { maxHeap.add(it) }

        while (maxHeap.size > 1) {
            val newStone = maxHeap.poll() - maxHeap.poll()
            if (newStone > 0) maxHeap.add(newStone)
        }

        return maxHeap.poll() ?: 0
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return root

        return if (q.`val` < root.`val` && p.`val` < root.`val`) lowestCommonAncestor(root.left, p, q)
        else if (q.`val` > root.`val` && p.`val` > root.`val`) lowestCommonAncestor(root.right, p, q)
        else root
    }

    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var moves = 0
        var currentX = points[0][0]
        var currentY = points[0][1]

        for (i in 1..points.lastIndex) {
            val targetX = points[i][0]
            val targetY = points[i][1]

            while (currentX != targetX || currentY != targetY) {
                val deltaX = currentX - targetX
                val deltaY = currentY - targetY

                when {
                    deltaX > 0 -> currentX--
                    deltaX < 0 -> currentX++
                }

                when {
                    deltaY > 0 -> currentY--
                    deltaY < 0 -> currentY++
                }

                moves++
            }
        }

        return moves
    }

    fun defangIPaddr(address: String): String =
        address.map { if (it == '.') "[.]" else it }.joinToString(separator = "")

    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        val setA = mutableSetOf<ListNode>()

        var currentNode = headA

        while (currentNode != null) {
            setA.add(currentNode)
            currentNode = currentNode.next
        }

        currentNode = headB

        while (currentNode != null) {
            if (setA.contains(currentNode)) return currentNode
            else currentNode = currentNode.next
        }

        return null
    }

    fun singleNumber(nums: IntArray): Int {
        val missingNumber = mutableSetOf<Int>()

        nums.forEach {
            if (missingNumber.contains(it)) missingNumber.remove(it)
            else missingNumber.add(it)
        }

        return missingNumber.first()
    }

    fun longestCommonPrefix(strs: Array<String>): String {
        var commonPrefix = strs[0]

        strs.forEach {
            while (it.startsWith(commonPrefix).not()) {
                commonPrefix = commonPrefix.dropLast(1)
            }
        }

        return commonPrefix
    }

    fun mergeTwoListsRecursive(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1

        return if (l1.`val` > l2.`val`) {
            l2.next = mergeTwoListsRecursive(l1, l2.next)
            l2
        } else {
            l1.next = mergeTwoListsRecursive(l1.next, l2)
            l1
        }
    }

    fun mostCommonWord(paragraph: String, banned: Array<String>): String {
        val frequency = mutableMapOf<String, Int>()
        val delimiters = arrayOf(" ", "!", "?", "'", ",", ";", ".")

        paragraph.toLowerCase().split(*delimiters).forEach {
            val key = it
            frequency[key] = frequency.getOrDefault(key, 0) + 1
        }

        val sortedFrequency = frequency.entries.sortedByDescending { it.value }

        sortedFrequency.forEach {
            val word = it.key

            if (banned.contains(word).not() && ("" == word).not()) return word
        }

        error("Word not found")
    }

    fun bubbleSort(array: IntArray) {
        for (index in array.indices) {
            for (index2 in index + 1..array.lastIndex) {
                if (array[index] > array[index2]) {
                    val temp = array[index2]
                    array[index2] = array[index]
                    array[index] = temp
                }
            }
        }
    }

    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null) return subRoot == null
        if (subRoot == null) return false

        var subTree = false

        if (root.`val` == subRoot.`val`) subTree =
            isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right)

        if (subTree.not()) subTree = isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)

        return subTree
    }

    fun isPalindrome(s: String): Boolean =
        s.toLowerCase().filter { it.isLetterOrDigit() }.let { it == it.reversed() }

    fun validPalindrome(s: String): Boolean {
        var lowerIndex = 0
        var upperIndex = s.lastIndex

        while (lowerIndex < upperIndex) {
            if (s[lowerIndex] != s[upperIndex]) {
                return isPalindrome(s.removeRange(lowerIndex, lowerIndex + 1)) || isPalindrome(
                    s.removeRange(
                        upperIndex, upperIndex + 1
                    )
                )
            }

            lowerIndex++
            upperIndex--
        }
        return true
    }

    fun pivotIndex(nums: IntArray): Int {
        var rightSum = nums.sum()
        var leftSum = 0

        nums.forEachIndexed { index, num ->
            rightSum -= num

            if (leftSum == rightSum) return index
            if (leftSum > rightSum) return -1

            leftSum += num
        }

        return -1
    }


    fun deleteDuplicates(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var current = head

        while (current != null) {
            if (current.`val` == prev?.`val`) prev.next = current.next
            else prev = current

            current = current.next
        }

        return head
    }

    var newHead: ListNode? = null

    fun reverseList2(head: ListNode?): ListNode? {
        reverseListRec(head)

        return newHead
    }

    fun reverseListRec(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) {

            newHead = head
            return head
        }

        val next = reverseListRec(head.next)

        head.next = null
        next?.next = head

        return head
    }

    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        val newHead = ListNode(0)
        var newHeadPointer: ListNode = newHead
        var pointer = head

        while (pointer != null) {
            if (pointer.`val` != `val`) {
                newHeadPointer.next = pointer
                newHeadPointer = pointer
            }

            pointer = pointer.next
        }

        newHeadPointer.next = null

        return newHead.next
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        var pointerL1 = list1
        var pointerL2 = list2

        val head = ListNode(0)
        var currentNode = head

        while (pointerL1 != null && pointerL2 != null) {
            if (pointerL1.`val` > pointerL2.`val`) {
                currentNode.next = pointerL2
                pointerL2 = pointerL2.next
            } else {
                currentNode.next = pointerL1
                pointerL1 = pointerL1.next
            }

            currentNode = currentNode.next!!
        }

        if (pointerL1 == null) currentNode.next = pointerL2
        else currentNode.next = pointerL1

        return head.next
    }

    fun hasCycle(head: ListNode?): Boolean {
        var hare = head?.next
        var tortoise = head

        while (hare != null && tortoise != null) {
            if (tortoise == hare) return true

            hare = hare.next?.next
            tortoise = tortoise.next
        }

        return false
    }

    fun isValid(s: String): Boolean {
        if (s.length % 2 != 0) return false

        val open = mutableMapOf(
            '(' to ')', '[' to ']', '{' to '}'
        )

        val queue = ArrayDeque<Char>()

        s.forEach {
            if (open.containsKey(it)) queue.add(it)
            else {
                if (queue.isEmpty()) return false

                if (it != open[queue.removeLast()]) return false
            }

        }

        return queue.isEmpty()
    }

    fun backspaceCompare(s: String, t: String): Boolean {
        val sb = StringBuilder()
        val tb = StringBuilder()

        s.forEach {
            if (it != '#') sb.append(it)
            else if (sb.isNotEmpty()) sb.deleteCharAt(sb.lastIndex)
        }

        t.forEach {
            if (it != '#') tb.append(it)
            else if (tb.isNotEmpty()) tb.deleteCharAt(tb.lastIndex)
        }

        return sb.toString() == tb.toString()
    }

    fun sortedArrayToBST(nums: IntArray): TreeNode? = if (nums.isEmpty()) null
    else toBST(nums, 0, nums.lastIndex)

    private fun toBST(nums: IntArray, lowerIndex: Int, upperIndex: Int): TreeNode? {
        if (lowerIndex > upperIndex) return null

        val middle = lowerIndex + (upperIndex - lowerIndex) / 2

        val node = TreeNode(nums[middle])

        node.left = toBST(nums, lowerIndex, middle - 1)
        node.right = toBST(nums, middle + 1, upperIndex)

        return node
    }

    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()

        nums.forEach {
            val absoluteValue = abs(it)
            val numberPos = absoluteValue - 1

            if (nums[numberPos] > 0) nums[numberPos] *= -1
        }

        nums.forEachIndexed { index, _ ->
            if (nums[index] > 0) result.add(index + 1)
        }

        return result
    }

    fun findTheDifference(s: String, t: String): Char {
        val frequency = mutableMapOf<Char, Int>()

        s.forEach { frequency[it] = frequency.getOrDefault(it, 0) + 1 }
        t.forEach { frequency[it] = frequency.getOrDefault(it, 0) - 1 }

        return frequency.entries.first { it.value == -1 }.key
    }

    fun flipGame(s: String): List<String> {
        val states = mutableListOf<String>()
        var index = 0

        while (index < s.lastIndex) {
            if (s[index] == '+' && s[index + 1] == '+') {
                states.add(s.replaceRange(index, index + 2, "--"))
            }

            index++
        }

        return states
    }

    fun flipAndInvertImage(image: Array<IntArray>): Array<IntArray> {
        image.forEach { row ->
            var last = row.lastIndex
            var index = 0

            while (last > index) {
                row[index] = row[last].also { row[last] = row[index] }

                index++
                last--
            }

            row.forEachIndexed { i, value -> row[i] = 1 - value }
        }

        return image
    }

    fun isHappy(n: Int): Boolean {
        var newNumber = endsHappy(n)
        val sums = mutableSetOf(newNumber)

        while (newNumber != 1) {
            newNumber = endsHappy(newNumber)

            if (sums.contains(newNumber)) return false
            else sums.add(newNumber)
        }

        return true
    }

    private fun endsHappy(n: Int): Int {
        var digit = n % 10
        var currentNumber = n / 10
        var newNumber = 0
        newNumber += digit * digit

        while (currentNumber > 0) {
            digit = currentNumber % 10
            currentNumber /= 10
            newNumber += digit * digit
        }

        return newNumber
    }

    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true

        var sPosition = 0;

        t.forEach {
            if (s[sPosition] == it) sPosition++
            if (sPosition == s.length) return true
        }

        return false
    }

    fun licenseKeyFormatting(s: String, k: Int): String {
        val sanitized = s.replace("-", "").toUpperCase()
        var index = sanitized.length % k
        val key = StringBuilder()

        if (sanitized.isEmpty()) return key.toString()

        if (index != 0) key.append(sanitized.substring(0, index), "-")

        while (index < sanitized.length) {
            key.append(sanitized.substring(index, index + k), "-")
            index += k
        }

        return key.substring(0, key.lastIndex)
    }


    fun firstBadVersion(n: Int): Int {
        var lowerIndex = 1
        var upperIndex = n

        while (lowerIndex < upperIndex) {
            var index = lowerIndex.plus(upperIndex.minus(lowerIndex).div(2))

            if (isBadVersion(index)) upperIndex = index
            else lowerIndex = index + 1
        }

        return lowerIndex
    }

    private fun isBadVersion(version: Int): Boolean = version > 1702766719

    fun searchInsert(nums: IntArray, target: Int): Int {
        var lowerIndex = 0
        var upperIndex = nums.lastIndex

        while (lowerIndex < upperIndex) {
            val index = lowerIndex + (upperIndex - lowerIndex) / 2
            val value = nums[index]

            if (value == target) return index

            if (target > value) lowerIndex = index + 1
            else upperIndex = index
        }

        return if (target > nums[lowerIndex]) lowerIndex + 1 else lowerIndex
    }

    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)

        var lowerIndex = 0
        var upperIndex = nums.lastIndex
        var resultIndex = nums.lastIndex

        while (resultIndex >= 0) {
            val index = if (Math.abs(nums[lowerIndex]) > Math.abs(nums[upperIndex])) {
                lowerIndex++
                lowerIndex - 1
            } else {
                upperIndex--
                upperIndex + 1
            }

            result[resultIndex] = nums[index] * nums[index]

            resultIndex--
        }

        return result
    }

    fun rotate(nums: IntArray, k: Int) {
        val uniqueRotations = k % nums.size

        reverse(nums, 0, nums.lastIndex)
        reverse(nums, 0, uniqueRotations - 1)
        reverse(nums, uniqueRotations, nums.lastIndex)
    }

    private fun reverse(nums: IntArray, from: Int, until: Int) {
        var lowerIndex = from
        var upperIndex = until

        while (lowerIndex < upperIndex) {
            val temp = nums[lowerIndex]

            nums[lowerIndex] = nums[upperIndex]
            nums[upperIndex] = temp

            lowerIndex++
            upperIndex--
        }
    }

    fun moveZeroes(nums: IntArray) {
        var fastIndex = 0

        nums.forEachIndexed { index, _ ->
            if (nums[index] == 0) {
                if (fastIndex == 0) fastIndex = index + 1

                while (fastIndex < nums.size) {
                    if (nums[fastIndex] != 0) {
                        nums[index] = nums[fastIndex]
                        nums[fastIndex] = 0
                        break
                    }

                    fastIndex++
                }
            }
        }
    }

    fun reverseWords(s: String): String {
        val result = s.toCharArray()

        var lowerIndex = 0
        var upperIndex = lowerIndex + 1

        while (upperIndex <= s.length) {
            if (upperIndex == s.length || s[upperIndex].isWhitespace()) {
                var innerUpperIndex = upperIndex - 1

                while (lowerIndex < innerUpperIndex) {
                    result[innerUpperIndex] = s[lowerIndex]
                    result[lowerIndex] = s[innerUpperIndex]

                    lowerIndex++
                    innerUpperIndex--
                }

                upperIndex++
                lowerIndex = upperIndex
            } else upperIndex++
        }

        return String(result)
    }

    fun middleNode(head: ListNode): ListNode {
        var count = 1
        var middle = head
        var pointer = head

        while (pointer.next != null) {
            pointer = pointer.next!!

            count++
            val moveMiddle = (count % 2) == 0

            if (moveMiddle) middle = middle.next!!
        }

        return middle
    }

    fun removeNthFromEnd(head: ListNode, n: Int): ListNode? {
        val start = ListNode(0)
        var fastPointer: ListNode? = head
        var slowPointer: ListNode? = start

        start.next = head

        repeat(n) { fastPointer = fastPointer?.next }

        while (fastPointer != null) {
            fastPointer = fastPointer?.next
            slowPointer = slowPointer?.next
        }

        slowPointer?.next = slowPointer?.next?.next

        return start.next
    }

    fun lengthOfLongestSubstring(s: String): Int {
        val prefix = StringBuilder()
        var maxLength = 0

        s.onEach {
            if (prefix.contains(it)) {
                if (prefix.length > maxLength) maxLength = prefix.length

                prefix.delete(0, prefix.indexOf(it) + 1)
            }

            prefix.append(it)
        }

        return max(maxLength, prefix.length)
    }

    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false

        val s1Frequency = IntArray(26)
        val s2WindowFrequency = IntArray(26)

        s1.forEachIndexed { index, c ->
            s1Frequency[c - 'a']++
            s2WindowFrequency[s2[index] - 'a']++
        }

        var index = s1.length
        while (index < s2.length) {
            if (sameFrequency(s1Frequency, s2WindowFrequency)) return true

            s2WindowFrequency[s2[index - s1.length] - 'a']--
            s2WindowFrequency[s2[index] - 'a']++
            index++
        }

        return sameFrequency(s1Frequency, s2WindowFrequency)
    }

    fun findAnagrams(s: String, p: String): List<Int> {
        val result = mutableListOf<Int>()
        val pFrequency = IntArray(26)
        val windowFrequency = IntArray(26)

        if (p.length > s.length) return result

        p.forEachIndexed { index, c ->
            pFrequency[c - 'a']++
            windowFrequency[s[index] - 'a']++
        }

        var rightWindowIndex = p.length
        while (rightWindowIndex < s.length) {
            val leftWindowIndex = rightWindowIndex - p.length

            if (sameFrequency(pFrequency, windowFrequency)) result.add(leftWindowIndex)

            windowFrequency[s[leftWindowIndex] - 'a']--
            windowFrequency[s[rightWindowIndex] - 'a']++

            rightWindowIndex++
        }

        if (sameFrequency(pFrequency, windowFrequency)) result.add(rightWindowIndex - p.length)

        return result
    }

    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        val workGrid = grid.clone()
        var area = 0

        for (row in workGrid.indices) for (column in workGrid[row].indices) {
            area = Math.max(isIsland(workGrid, row, column), area)
        }

        return area
    }

    private fun isIsland(grid: Array<IntArray>, row: Int, column: Int): Int {
        if (row !in grid.indices || column !in grid[row].indices || grid[row][column] == 0) return 0

        grid[row][column] = 0

        val currentArea = isIsland(grid, row + 1, column) + isIsland(grid, row - 1, column) + isIsland(
            grid, row, column + 1
        ) + isIsland(grid, row, column - 1)

        return 1 + currentArea
    }

    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null) return root2
        if (root2 == null) return root1

        val root = TreeNode(root1.`val` + root2.`val`)
        root.left = mergeTrees(root1.left, root2.left)
        root.right = mergeTrees(root1.right, root2.right)

        return root
    }

    fun connect(root: Node?): Node? {
        if (root == null) return null

        root.left?.next = root.right
        connect(root.left)

        root.right?.next = root.next?.left
        connect(root.right)

        return root
    }

    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val result = mat.clone()
        val maxDistance = mat.size + mat[0].size

        mat.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, _ ->
                if (mat[rowIndex][columnIndex] != 0) {
                    var topDistance = maxDistance
                    var leftDistance = maxDistance
                    val leftIndex = columnIndex - 1
                    val topIndex = rowIndex - 1

                    if (topIndex >= 0) topDistance = result[topIndex][columnIndex] + 1
                    if (leftIndex >= 0) leftDistance = result[rowIndex][leftIndex] + 1

                    result[rowIndex][columnIndex] = Math.min(topDistance, leftDistance)
                }
            }
        }

        var rowIndex = mat.lastIndex
        while (rowIndex >= 0) {
            var columnIndex = mat[rowIndex].lastIndex

            while (columnIndex >= 0) {
                if (mat[rowIndex][columnIndex] != 0) {
                    val bottomIndex = rowIndex + 1
                    val rightIndex = columnIndex + 1
                    var bottomDistance = maxDistance
                    var rightDistance = maxDistance

                    if (bottomIndex < mat.size) bottomDistance = mat[bottomIndex][columnIndex] + 1
                    if (rightIndex < mat[rowIndex].size) rightDistance = mat[rowIndex][rightIndex] + 1


                    result[rowIndex][columnIndex] =
                        Math.min(result[rowIndex][columnIndex], Math.min(bottomDistance, rightDistance))
                }
                columnIndex--
            }

            rowIndex--
        }

        return result
    }

    fun orangesRotting(grid: Array<IntArray>): Int {
        var minutes = 0
        var rottedOrange: Boolean

        do {
            rottedOrange = false

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, cell ->
                    if (cell == 3) grid[rowIndex][columnIndex] = 2
                }
            }

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, cell ->
                    if (cell == 1 && isRottedOrangeClose(grid, rowIndex, columnIndex)) {
                        rottedOrange = true

                        grid[rowIndex][columnIndex] = 3
                    }
                }
            }

            if (rottedOrange) minutes++
        } while (rottedOrange)

        grid.forEach { row ->
            row.forEach { cell -> if (cell == 1) return -1 }
        }

        return minutes
    }

    fun mergeTwoListsIterative(list1: ListNode?, list2: ListNode?): ListNode? {
        var pointerA: ListNode? = list1
        var pointerB: ListNode? = list2
        var pointer = ListNode(-1)
        val head = pointer


        while (pointerA != null && pointerB != null) {
            val next = if (pointerA.`val` > pointerB.`val`) pointerB.also { pointerB = it.next }
            else pointerA.also { pointerA = it.next }

            pointer.next = next
            pointer = next
        }

        if (pointerA == null) pointer.next = pointerB
        else pointer.next = pointerA

        return head.next
    }

    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var previous: ListNode? = null
        var pointer: ListNode? = head

        do {
            val temp = pointer?.next
            pointer?.next = previous

            previous = pointer
            pointer = temp
        } while (pointer?.next != null)

        pointer?.next = previous

        return pointer
    }

    // Pre-order traversal is to visit the root first. Then traverse the left subtree. Finally, traverse the right subtree.
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        preorderTraversal(root, result)

        return result
    }

    private fun preorderTraversal(root: TreeNode?, result: MutableList<Int>) {
        if (root == null) return

        result.add(root.`val`)
        preorderTraversal(root.left, result)
        preorderTraversal(root.right, result)
    }

    // In-order traversal is to traverse the left subtree first. Then visit the root. Finally, traverse the right subtree.
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        inorderTraversal(root, result)

        return result
    }

    private fun inorderTraversal(root: TreeNode?, result: MutableList<Int>) {
        if (root == null) return

        inorderTraversal(root.left, result)
        result.add(root.`val`)
        inorderTraversal(root.right, result)
    }

    // Post-order traversal is to traverse the left subtree first. Then traverse the right subtree. Finally, visit the root.
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        inorderTraversal(root, result)

        return result
    }


    private fun postorderTraversal(root: TreeNode?, result: MutableList<Int>) {
        if (root == null) return

        postorderTraversal(root.left, result)
        postorderTraversal(root.right, result)
        result.add(root.`val`)
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        if (root == null) return result

        val deque = ArrayDeque(listOf(root))

        while (deque.isNotEmpty()) {
            val levelValues = mutableListOf<Int>()
            val nextLevelNodes = mutableListOf<TreeNode>()

            deque.forEach {
                levelValues.add(it.`val`)

                it.left?.also { node -> nextLevelNodes.add(node) }
                it.right?.also { node -> nextLevelNodes.add(node) }
            }

            deque.clear()
            deque.addAll(nextLevelNodes)
            result.add(levelValues)
        }

        return result
    }

    private fun isRottedOrangeClose(grid: Array<IntArray>, rowIndex: Int, columnIndex: Int) =
        checkIsRottedOrangeClose(grid, rowIndex + 1, columnIndex) || checkIsRottedOrangeClose(
            grid, rowIndex - 1, columnIndex
        ) || checkIsRottedOrangeClose(grid, rowIndex, columnIndex + 1) || checkIsRottedOrangeClose(
            grid, rowIndex, columnIndex - 1
        )

    private fun checkIsRottedOrangeClose(grid: Array<IntArray>, rowIndex: Int, columnIndex: Int): Boolean {
        if (rowIndex !in grid.indices || columnIndex !in grid[rowIndex].indices) return false

        return grid[rowIndex][columnIndex] == 2
    }

    fun isSymmetric(root: TreeNode?): Boolean = isSymmetric(root?.left, root?.right)

    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left?.`val` != right?.`val`) return false

        return isSymmetric(left?.left, right?.right) && isSymmetric(left?.right, right?.left)
    }

    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        val currentTarget = targetSum - root.`val`

        if (currentTarget == 0 && root.left == null && root.right == null) return true

        return hasPathSum(root.left, currentTarget) || hasPathSum(root.right, currentTarget)
    }

    var univalSubtress = 0

    fun countUnivalSubtrees(root: TreeNode?): Int {
        isUnivalSubtrees(root)

        return univalSubtress
    }

    fun isUnivalSubtrees(root: TreeNode?): Boolean {
        if (root == null) return true
        val left = root.left
        val right = root.right

        if (left == null && right == null) {
            univalSubtress++
            return true
        }

        var isUnival = isUnivalSubtrees(root.left).and(isUnivalSubtrees(root.right))

        if (left != null) isUnival = isUnival && root.`val` == left.`val`
        if (right != null) isUnival = isUnival && root.`val` == right.`val`

        if (isUnival) univalSubtress++

        return isUnival
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val response = IntArray(2)
        val map = mutableMapOf<Int, MutableList<Int>>()

        nums.forEachIndexed { index, value ->
            map[value] = map.getOrDefault(value, mutableListOf()).also { it.add(index) }
        }

        nums.forEachIndexed { index, value ->
            val lookUp = target - value
            val hasTarget = map[lookUp]

            if (hasTarget != null) {
                val containsElement = hasTarget.contains(index)
                if (containsElement && hasTarget.size > 1) {
                    hasTarget.remove(index)
                    return intArrayOf(index, hasTarget[0])
                }
                if (containsElement.not()) {
                    return intArrayOf(index, hasTarget[0])
                }
            }
        }

        return response
    }


    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var nums2Index = nums2.lastIndex
        var nums1Index = m - 1

        for (responseIndex in nums1.lastIndex downTo 0) {
            val val1 = if (nums1Index in nums1.indices) nums1[nums1Index] else MIN_VALUE
            val val2 = if (nums2Index in nums1.indices) nums2[nums2Index] else MIN_VALUE
            val responseVal = if (val1 > val2) {
                nums1Index--
                val1
            } else {
                nums2Index--
                val2
            }

            nums1[responseIndex] = responseVal
        }
    }

    fun fib(n: Int): Int {
        if (n <= 1) return n

        val cache = IntArray(n + 1)
        cache[1] = 1

        for (index in 2..n) {
            cache[index] = cache[index - 2] + cache[index - 1]
        }

        return cache[n]
    }

    fun minimumAbsDifference(arr: IntArray): List<List<Int>> {
        arr.sort()

        val minDiffs = mutableListOf<List<Int>>()
        var minDiff = Int.MAX_VALUE

        for (index in 1..arr.lastIndex) {
            val left = arr[index - 1]
            val right = arr[index]
            val diff = right - left

            if (diff < minDiff) {
                minDiff = diff
                minDiffs.clear()
                minDiffs.add(listOf(left, right))
            } else if (diff == minDiff) minDiffs.add(listOf(left, right))
        }

        return minDiffs
    }

    fun maxSubArray(nums: IntArray): Int {
        var maxSum = nums[0]
        var currentSum = nums[0]

        for (index in 1..nums.lastIndex) {
            currentSum = Math.max(nums[index], currentSum + nums[index])
            maxSum = Math.max(maxSum, currentSum)
        }

        return maxSum
    }

    fun containsDuplicate(nums: IntArray): Boolean {
        val map = mutableMapOf<Int, Int>()

        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

        return map.values.any { it > 1 }
    }

    fun intersectSorted(nums1: IntArray, nums2: IntArray): IntArray {
        val response = mutableListOf<Int>()

        nums1.sort()
        nums2.sort()

        var index1 = 0
        var index2 = 0

        while (index1 < nums1.size && index2 < nums2.size) {
            val num1 = nums1[index1]
            val num2 = nums1[index2]

            if (num1 > num2) index2++
            else if (num2 > num1) index1++
            else {
                response.add(num1)
                index1++
                index2++
            }
        }

        return response.toIntArray()
    }

    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var buyPrice = prices[0]

        for (index in 1..prices.lastIndex) {
            buyPrice = Math.min(buyPrice, prices[index - 1])
            maxProfit = Math.max(maxProfit, prices[index] - buyPrice)
        }

        return maxProfit
    }

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums1.size > nums2.size) return intersect(nums2, nums1)

        val response = mutableListOf<Int>()
        val map2 = mutableMapOf<Int, Int>()

        nums2.forEach {
            map2[it] = map2.getOrDefault(it, 0) + 1
        }


        nums1.forEach {
            val qtd = map2[it]

            if (qtd != null && qtd > 0) {
                response.add(it)
                map2[it] = qtd - 1
            }
        }

        return response.toIntArray()
    }


    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        val rows = mat.size
        val columns = mat[0].size
        if (rows * columns != r * c) return mat
        if (rows == r && columns == c) return mat

        val response = Array(r) { IntArray(c) }

        var indexRow = 0
        var indexColumn = 0

        mat.forEach { row ->
            row.forEach { value ->
                response[indexRow][indexColumn++] = value

                if (indexColumn >= c) {
                    indexRow++
                    indexColumn = 0
                }
            }
        }

        return response
    }

    fun generate(numRows: Int): List<List<Int>> {
        var row = 1
        val result = mutableListOf<List<Int>>()
        result.add(listOf(1))

        repeat(numRows - 1) {
            var index = 0
            val previousRow = result[row - 1]
            val rowValues = mutableListOf<Int>()

            repeat(row + 1) {
                rowValues.add(previousRow.getOrElse(index - 1) { 0 } + previousRow.getOrElse(index) { 0 })

                index++
            }

            row++
            result.add(rowValues)
        }

        return result
    }

    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val rows = Array(9) { mutableSetOf<Char>() }
        val blocks = Array(9) { mutableSetOf<Char>() }
        val columns = Array(9) { mutableSetOf<Char>() }

        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, value ->
                if (value.isDigit()) {
                    val blockIndex = (rowIndex / 3) * 3 + columnIndex / 3

                    if (rows[rowIndex].add(value).not()) return false
                    if (blocks[blockIndex].add(value).not()) return false
                    if (columns[columnIndex].add(value).not()) return false
                }
            }
        }

        return true
    }

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rows = matrix.size
        val column = matrix[0].size

        var left = 0
        var right = rows * column - 1
        var pivotIdx = 0
        var pivotElement = 0

        while (left <= right) {
            pivotIdx = (left + right) / 2
            pivotElement = matrix[pivotIdx / column][pivotIdx % column]

            if (target == pivotElement) return true

            if (target < pivotElement) right = pivotIdx - 1
            else left = pivotIdx + 1
        }

        return false
    }

    fun firstUniqChar(s: String): Int {
        val frequency = IntArray(26)

        s.forEach {
            frequency[it - 'a']++
        }

        s.forEachIndexed { index, char ->
            if (frequency[char - 'a'] == 1) return index
        }

        return -1
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val ransomNoteFrequency = IntArray(26)
        val magazineFrequency = IntArray(26)

        ransomNote.forEach {
            ransomNoteFrequency[it - 'a']++
        }

        magazine.forEach {
            magazineFrequency[it - 'a']++
        }

        ransomNoteFrequency.forEachIndexed { index, charFrequency ->
            if (magazineFrequency[index] < charFrequency) return false
        }

        return true
    }

    fun isAnagram(s: String, t: String): Boolean {
        val sFrequency = IntArray(26)
        val tFrequency = IntArray(26)

        s.forEach {
            sFrequency[it - 'a']++
        }

        t.forEach {
            tFrequency[it - 'a']++
        }

        sFrequency.forEachIndexed { index, sCharFreq ->
            if (tFrequency[index] != sCharFreq) return false
        }

        return true
    }

    fun inorderTraversalIT(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val result = mutableListOf<Int>()

        var node = root

        while (node != null || stack.isNotEmpty()) {
            while (node != null) {
                stack.add(node)
                node = node.left
            }

            node = stack.pop()
            result.add(node.`val`)
            node = node.right
        }

        return result
    }


    fun postorderTraversalIT(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val result = mutableListOf<Int>()

        if (root != null) stack.add(root)

        while (stack.isNotEmpty()) {
            val node = stack.pop()

            result.add(0, node.`val`)
            if (node.left != null) stack.add(node.left)
            if (node.right != null) stack.add(node.right)
        }

        return result
    }

    fun hasPathSumIT(root: TreeNode?, targetSum: Int): Boolean {
        val queue = mutableListOf<Pair<Int, TreeNode>>()

        root?.also { queue.add(targetSum - it.`val` to it) }

        while (queue.isNotEmpty()) {
            val (currentSum, node) = queue.removeAt(0)

            if (node.left == null && node.right == null && currentSum == 0) return true
            node.left?.also { queue.add(currentSum - it.`val` to it) }
            node.right?.also { queue.add(currentSum - it.`val` to it) }
        }

        return false
    }

    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        val newNode = TreeNode(`val`)
        if (root == null) return newNode

        var node = root

        while (node != null) {
            if (`val` > node.`val`) {
                if (node.right == null) {
                    node.right = newNode
                    node = null
                } else node = node.right
            } else if (`val` < node.`val`) {
                if (node.left == null) {
                    node.left = newNode
                    node = null
                } else node = node.left
            }
        }

        return root
    }

    fun findTarget(root: TreeNode?, k: Int): Boolean {
        if (root == null) return false
        val queue = mutableListOf<TreeNode>()

        root.also { queue.add(it) }

        while (queue.isNotEmpty()) {
            val node = queue.removeAt(0)

            val target = k - root.`val`
            if (target != node.`val` && binarySearch(root, target)) return true

            node.left?.also { queue.add(it) }
            node.right?.also { queue.add(it) }
        }

        return false
    }

    private fun binarySearch(root: TreeNode, value: Int): Boolean {
        var node: TreeNode? = root

        while (node != null) {
            if (value == node.`val`) return true

            node = if (value > node.`val`) node.right else node.left
        }

        return false
    }

    fun isValidBST(root: TreeNode?): Boolean {
        var node = root
        var previousValue: Int? = null
        val queue = mutableListOf<TreeNode>()

        while (queue.isNotEmpty() || node != null) {
            while (node?.left != null) {
                queue.add(node)
                node = node.left
            }

            if (node == null) node = queue.removeAt(queue.lastIndex)

            if (previousValue != null && previousValue >= node.`val`) return false

            previousValue = node.`val`

            node = node.right
        }

        return true
    }

    fun invertTree(root: TreeNode?): TreeNode? {
        val stack = mutableListOf<TreeNode>()

        root?.also { stack.add(it) }

        while (stack.isNotEmpty()) {
            val node = stack.removeAt(0)
            node.left?.also { stack.add(it) }
            node.right?.also { stack.add(it) }

            val temp = node.left
            node.left = node.right
            node.right = temp
        }

        return root
    }

    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        var node = root

        while (node != null) {
            if (node.`val` == `val`) return node
            node = if (`val` > node.`val`) node.right else node.left
        }

        return null
    }

    fun isSymmetricIT(root: TreeNode?): Boolean {
        val stack = mutableListOf<TreeNode?>()

        stack.add(root?.left)
        stack.add(root?.right)

        while (stack.isNotEmpty()) {
            val left = stack.removeAt(0)
            val right = stack.removeAt(0)

            if (left?.`val` != right?.`val`) return false

            left?.also { stack.add(it.left) }
            right?.also { stack.add(it.right) }

            left?.also { stack.add(it.right) }
            right?.also { stack.add(it.left) }
        }

        return true
    }

    fun maxDepth(root: TreeNode?): Int {
        var maxDepth = 0
        val stackDepth = mutableListOf<Int>()
        val stackNodes = mutableListOf<TreeNode>()

        root?.also {
            stackNodes.add(it)
            stackDepth.add(1)
        }

        while (stackNodes.isNotEmpty()) {
            val node = stackNodes.removeAt(stackNodes.lastIndex)
            val depth = stackDepth.removeAt(stackDepth.lastIndex)

            maxDepth = maxDepth.coerceAtLeast(depth)

            node.left?.also {
                stackNodes.add(it)
                stackDepth.add(depth + 1)
            }

            node.right?.also {
                stackNodes.add(it)
                stackDepth.add(depth + 1)
            }
        }

        return maxDepth
    }

    fun levelOrder2(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val treeLevelNodes = mutableListOf<TreeNode>()

        root?.also { treeLevelNodes.add(it) }

        while (treeLevelNodes.isNotEmpty()) {
            val levelValues = mutableListOf<Int>()

            for (index in treeLevelNodes.indices) {
                val node = treeLevelNodes.removeAt(0)
                levelValues.add(node.`val`)

                node.left?.also { treeLevelNodes.add(it) }
                node.right?.also { treeLevelNodes.add(it) }
            }

            result.add(levelValues)
        }

        return result
    }

    fun preorderTraversalIT(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val response = mutableListOf<Int>()

        if (root != null) stack.add(root)

        while (stack.isNotEmpty()) {
            val node = stack.pop()

            response.add(node.`val`)

            if (node.right != null) stack.add(node.right)
            if (node.left != null) stack.add(node.left)
        }

        return response
    }

    fun areAlmostEqual(s1: String, s2: String): Boolean {
        val diff = mutableListOf<Int>()

        s1.forEachIndexed { index, value ->
            if (s2[index] != value) {
                diff.add(index)
            }
        }

        if (diff.size == 0) return true
        if (diff.size != 2) return false

        return s1[diff[0]] == s2[diff[1]] && s1[diff[1]] == s2[diff[0]]
    }

    fun subtractProductAndSum(n: Int): Int {
        var sum = 0
        var number = n
        var product = 1

        while (number > 0) {
            val digit = number % 10
            sum += digit
            product *= digit

            number /= 10

        }

        return product - sum
    }

    fun average(salary: IntArray): Double {
        var maxSalary = MIN_VALUE
        var minSalary = Int.MAX_VALUE
        var average = 0

        salary.forEach {
            average += it

            maxSalary = maxOf(maxSalary, it)
            minSalary = minOf(minSalary, it)
        }

        average -= maxSalary
        average -= minSalary

        return average.toDouble() / (salary.size - 2)
    }

    fun countOdds(low: Int, high: Int): Int {
        if (low == high && high % 2 == 1) return 1

        val odds = (high - low) / 2

        return if (low % 2 == 1 || high % 2 == 1) odds + 1
        else odds
    }

    fun missingElement(nums: IntArray, k: Int): Int {
        var missing = 0
        var missingVal = nums[0]
        var prevValue = missingVal

        nums.forEach {
            val diff = it - prevValue

            if (diff > 1) {
                missingVal = prevValue

                repeat(diff - 1) {
                    missing++
                    missingVal++

                    if (missing == k) return missingVal
                }
            }

            prevValue = it
        }

        return nums[nums.lastIndex] + k - missing
    }

    fun searchRange(nums: IntArray, target: Int): IntArray {
        var leftIndex = 0
        var rightIndex = nums.lastIndex

        while (leftIndex <= rightIndex) {
            val middle = leftIndex + (rightIndex - leftIndex) / 2

            if (nums[middle] == target) {
                var end = middle
                var start = middle

                while (start > 1 && nums[start - 1] == target) start--
                while (end < nums.lastIndex && nums[end + 1] == target) end++

                return intArrayOf(start, end)
            } else if (target > nums[middle]) leftIndex = middle + 1
            else rightIndex = middle - 1
        }

        return intArrayOf(-1, -1)
    }

    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {
        val result = mutableListOf<String>()

        var currentLower = lower - 1

        nums.forEach {
            val upperValue = it - 1
            val lowerValue = currentLower + 1

            if (upperValue - lowerValue == 0) result.add("$upperValue")
            if (upperValue - lowerValue >= 1) result.add("$lowerValue->$upperValue")

            currentLower = it
        }

        val lowerValue = currentLower + 1

        if (upper - lowerValue == 0) result.add("$upper")
        if (upper - lowerValue >= 1) result.add("$lowerValue->$upper")

        return result
    }

    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) return false
        val firstHalfEnd = endFirstHalf(head)
        val secondHalfStart = reverseList3(firstHalfEnd?.next)

        var node1 = head
        var node2 = secondHalfStart
        var result = true

        while (result && node2 != null) {
            if (node1?.`val` != node2.`val`) result = false

            node1 = head.next
            node2 = node2.next
        }

        firstHalfEnd?.next = reverseList3(secondHalfStart)

        return result
    }

    private fun reverseList3(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var node = head

        while (node != null) {
            val temp = node.next
            node.next = prev
            prev = node
            node = temp
        }

        return prev
    }

    private fun endFirstHalf(head: ListNode): ListNode? {
        var slowNode: ListNode? = head
        var fastNode: ListNode? = slowNode

        while (fastNode?.next?.next != null) {
            slowNode = slowNode?.next
            fastNode = fastNode.next?.next
        }

        return slowNode
    }

    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortBy { it[0] }

        var previousEnd = MIN_VALUE

        intervals.forEach {
            if (previousEnd > it[0]) return false
            else previousEnd = it[1]
        }

        return true
    }

    fun twoSumLessThanK(nums: IntArray, k: Int): Int {
        nums.sort()
        var maxSum = -1

        var lowerIndex = 0
        var upperIndex = nums.lastIndex

        while (lowerIndex != upperIndex) {
            val sum = nums[upperIndex] + nums[lowerIndex]

            if (sum >= k) upperIndex--
            else {
                maxSum = maxSum.coerceAtLeast(sum)
                lowerIndex++
            }
        }

        return maxSum
    }

    fun isStrobogrammatic(num: String): Boolean {
        val canRotate = mapOf('0' to '0', '1' to '1', '8' to '8', '6' to '9', '9' to '6')

        if (num.length == 1) return num[0] == canRotate[num[0]]

        var lowerIndex = 0
        var upperIndex = num.lastIndex

        while (lowerIndex < upperIndex) {
            val lowerChar = canRotate[num[lowerIndex]]
            val upperChar = canRotate[num[upperIndex]]

            if (lowerChar == null || upperChar == null) return false
            if (upperChar != num[lowerIndex]) return false

            lowerIndex++
            upperIndex--
        }

        return true
    }

    fun sortArrayByParityII(nums: IntArray): IntArray {
        var oddIndex = 1
        var evenIndex = 0

        while (oddIndex < nums.size && evenIndex < nums.lastIndex) {
            if (nums[oddIndex] % 2 == 1) oddIndex += 2
            else if (nums[evenIndex] % 2 == 0) evenIndex += 2
            else {
                val temp = nums[evenIndex]
                nums[evenIndex] = nums[oddIndex]
                nums[oddIndex] = temp
            }
        }

        return nums
    }

    fun missingNumber(arr: IntArray): Int {
        val progression = (arr[arr.lastIndex] - arr[0]) / arr.size
        var leftIndex = 0
        var rightIndex = arr.lastIndex

        while (rightIndex > leftIndex) {
            val middle = leftIndex + (rightIndex - leftIndex) / 2
            val shouldBe = arr[0] + middle * progression

            if (arr[middle] == shouldBe) leftIndex = middle + 1
            else rightIndex = middle
        }

        return arr[0] + leftIndex * progression
    }

    fun ladderLength(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
        var transformations = 0
        val bfs = mutableListOf<String>()
        bfs.add(beginWord)
        wordList.remove(beginWord)

        while (bfs.isNotEmpty()) {
            val times = bfs.size
            transformations++

            repeat(times) {
                val word = bfs.removeAt(0)
                if (word == endWord) return transformations

                possibleNextWords(word, wordList).forEach {
                    bfs.add(it)
                    wordList.remove(it)
                }
            }
        }

        return 0
    }

    private fun possibleNextWords(beginWord: String, wordList: List<String>): List<String> {
        val possibleNextWords = mutableSetOf<String>()

        for (index in beginWord.indices) {
            val possibleWord = beginWord.toCharArray()

            for (char in 'a'..'z') {
                possibleWord[index] = char

                possibleNextWords.add(String(possibleWord))
            }
        }

        return wordList.mapNotNull { if (possibleNextWords.contains(it)) it else null }
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        val columns = TreeMap<Int, MutableList<Int>>(naturalOrder())

        val queue = mutableListOf<Pair<TreeNode, Int>>()

        root?.also { queue.add(it to 0) }

        while (queue.isNotEmpty()) {
            val levelDist = mutableMapOf<Int, MutableList<Int>>()

            for (level in queue.indices) {
                val (node, column) = queue.removeAt(0)

                levelDist.getOrPut(column) { mutableListOf() }.add(node.`val`)

                node.left?.also { queue.add(it to column - 1) }
                node.right?.also { queue.add(it to column + 1) }
            }

            levelDist.forEach {
                columns.getOrPut(it.key) { mutableListOf() }.addAll(it.value.sorted())
            }
        }

        return columns.values.toList()
    }

    fun largestRectangleArea(heights: IntArray): Int {
        var maxArea = 0
        val stack = mutableListOf<Int>()

        stack.add(-1)

        heights.forEachIndexed { limit, value ->
            while (stack.last() != -1 && value < heights[stack.last()]) {
                val barValue = heights[stack.removeAt(stack.lastIndex)]
                val leftLimit = stack.last()

                val area = barValue * (limit - leftLimit - 1)
                maxArea = Math.max(maxArea, area)
            }

            stack.add(limit)
        }

        while (stack.last() != -1) {
            val barValue = heights[stack.removeAt(stack.lastIndex)]
            val leftLimit = stack.last()

            val area = barValue * (heights.lastIndex - leftLimit)
            maxArea = Math.max(maxArea, area)
        }

        return maxArea
    }

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()

        intervals.sortBy { it[0] }
        result.add(intervals[0])

        for (index in 1..intervals.lastIndex) {
            val lastInterval = result.last()
            val (start, end) = intervals[index]

            if (lastInterval[1] in start..end) lastInterval[1] = end
            else if (end !in lastInterval[0]..lastInterval[1]) result.add(intervals[index])
        }

        return result.toTypedArray()
    }

    fun subarraySum(nums: IntArray, k: Int): Int {
        var subArrays = 0
        val contiguousSubArraysSum = mutableMapOf<Int, Int>()
        var currentSum = 0

        contiguousSubArraysSum[0] = 1

        nums.forEach {
            currentSum += it
            val complement = currentSum - k

            subArrays += contiguousSubArraysSum[complement] ?: 0
            contiguousSubArraysSum[currentSum] = (contiguousSubArraysSum[currentSum] ?: 0) + 1
        }

        return subArrays
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequency = mutableMapOf<Int, Int>()
        val binSort = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }

        nums.forEach { frequency[it] = (frequency[it] ?: 0) + 1 }

        frequency.forEach { (element, frequency) -> binSort[frequency].add(element) }

        val sortedElementsAsc = binSort.filter { it.isNotEmpty() }.flatten()

        return sortedElementsAsc.subList(sortedElementsAsc.size - k, sortedElementsAsc.size).toIntArray()
    }

}


private fun sameFrequency(firstFrequency: IntArray, secondFrequency: IntArray): Boolean {
    firstFrequency.forEachIndexed { index, frequency ->
        if (secondFrequency[index] != frequency) return false
    }

    return true
}

class TreeNode(
    var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null
) {
    override fun toString(): String = "$`val`"
}

class ListNode(var `val`: Int, var next: ListNode? = null) {
    fun toList(): List<Int> {
        var pointer: ListNode? = this
        val result = mutableListOf<Int>()

        while (pointer != null) {
            result.add(pointer.`val`)

            pointer = pointer.next
        }

        return result
    }

    override fun toString(): String = "$`val`" + if (next != null) " -> $next" else ""
}

class Node(
    var `val`: Int, var left: Node? = null, var right: Node? = null, var next: Node? = null
) {
    override fun toString(): String = "$`val`" + if (next != null) " -> $next" else ""
}


class MyQueue() {
    val input = Stack<Int>()
    val output = Stack<Int>()

    fun push(x: Int) = input.add(x)

    fun pop(): Int {
        input.peek()

        return output.pop()
    }

    fun peek(): Int {
        while (input.isNotEmpty() && output.isEmpty()) {
            output.add(input.pop())
        }

        return output.peek()
    }

    fun empty(): Boolean = input.isEmpty() && output.isEmpty()

}

class NumArray(nums: IntArray) {
    val sum = IntArray(nums.size + 1)

    init {
        nums.forEachIndexed { index, value ->
            sum[index + 1] = sum[index] + value
        }
    }

    fun sumRange(left: Int, right: Int): Int = sum[right + 1] - sum[left]

}