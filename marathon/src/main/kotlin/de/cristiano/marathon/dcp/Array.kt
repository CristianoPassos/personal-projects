package de.cristiano.marathon.dcp

class Array {

    fun productExceptSelf(nums: IntArray): IntArray {
        val prefixArray = IntArray(nums.size)
        val postfixArray = IntArray(nums.size)
        val answer = IntArray(nums.size)


        for (index in nums.indices) {
            val lastValue = if (index - 1 < 0) 1 else prefixArray[index - 1]

            prefixArray[index] = lastValue * nums[index]
        }

        for (index in nums.lastIndex downTo 0) {
            val lastValue = if (index + 1 > nums.lastIndex) 1 else postfixArray[index + 1]

            postfixArray[index] = lastValue * nums[index]
        }

        for (index in answer.indices) {
            val prefixValue = if (index - 1 < 0) 1 else prefixArray[index - 1]
            val postfixValue = if (index + 1 > postfixArray.lastIndex) 1 else postfixArray[index + 1]

            answer[index] = prefixValue * postfixValue
        }

        return answer
    }

    fun minSortingWindow(nums: IntArray): Pair<Int, Int> {
        var rightWindow = nums[0] to 0
        var leftWindow = nums[nums.lastIndex] to nums.lastIndex


        nums.forEachIndexed { index, num ->
            if (rightWindow.first > num) rightWindow = rightWindow.first to index
            if (num > rightWindow.first) rightWindow = num to rightWindow.second
        }

        for (index in nums.lastIndex downTo 0) {
            if (leftWindow.first < nums[index]) leftWindow = leftWindow.first to index
            if (nums[index] < leftWindow.first) leftWindow = nums[index] to leftWindow.second
        }

        return leftWindow.second to rightWindow.second
    }
}
