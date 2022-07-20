package de.cristiano.marathon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExponentProblemsTest() {

    private val serializeAndDeserializeStringArray = SerializeAndDeserializeStringArray()
    private val partitionEqualSubsetSum = PartitionEqualSubsetSum()

    @Test
    fun serializeAndDeserializeStringArrayTest() {
        val stringArray = arrayOf(
            "Test1",
            """Test1\"/&%§/&!§OJ!\"LJKHSD""",
            "Test1-Test2",
            "Test1Test1, 123,1231",
            "Test1, 123",
        "Test1 Test2"
        )

        val serialized = serializeAndDeserializeStringArray.serialize(stringArray)
        val deserialized = serializeAndDeserializeStringArray.deserialize(serialized)

        assertThat(stringArray).isEqualTo(deserialized)
    }

    @Test
    fun partitionEqualSubsetSumTest() {
        // val canPartition = partitionEqualSubsetSum.canPartition(intArrayOf(1, 5, 11, 5))
        // assertThat(canPartition).isTrue
    }
}