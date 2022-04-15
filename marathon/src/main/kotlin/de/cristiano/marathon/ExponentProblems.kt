package de.cristiano.marathon

/**
 * Given an array of string, to serialize and deserialize it
 */
class SerializeAndDeserializeStringArray() {

    fun serialize(strings: Array<String>): String = strings.joinToString(separator = "") { it.length.toChar() + it }

    fun deserialize(string: String): Array<String> {
        println("Serialized string: $string")

        var index = 0
        val strings = mutableListOf<String>()

        while (index < string.length) {
            val stringLength = string[index].hashCode()
            val startString = index + 1
            val endString = startString + stringLength

            strings.add(string.substring(startString, endString))

            index = endString
        }

        return strings.toTypedArray()
    }
}

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
class PartitionEqualSubsetSum {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false

        return canPartition(nums, sum / 2, 0)
    }

    fun canPartition(nums: IntArray, target: Int, positionToRemove: Int): Boolean {
        if (positionToRemove > nums.size) return false

        if (target - nums[positionToRemove] == target) return false

        error("not implemented")
    }
}


/**
 * You are given a string S, consisting of N digits, that represents a number.
 * You can change at most one digit in the string to any other digit.
 * How many different numbers divisible by 3 can be obtained in this way?
 */
fun howManyDivisibleByThree(string: String): Int {
    error("Todo")
}
