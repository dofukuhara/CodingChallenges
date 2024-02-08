package maximumsubarray

class MaximumSubarray {
    init {
        val codeChallenge =
            "\n" +
            "************************* Maximum Subarray *************************\n" +
            "* Given an integer array nums, find the subarray with the largest  *\n" +
            "* sum, and return its sum.                                         *\n" +
            "*                                                                  *\n" +
            "* Example 1:                                                       *\n" +
            "*    | Input: nums = [-2,1,-3,4,-1,2,1,-5,4]                       *\n" +
            "*    | Output: 6                                                   *\n" +
            "*    | Explanation: The subarray [4,-1,2,1] has the largest sum 6. *\n" +
            "*                                                                  *\n" +
            "Example 2:                                                         *\n" +
            "*    | Input: nums = [1]                                           *\n" +
            "*    | Output: 1                                                   *\n" +
            "*    | Explanation: The subarray [1] has the largest sum 1.        *\n" +
            "*                                                                  *\n" +
            "Example 3:                                                         *\n" +
            "*    | Input: nums = [5,4,-1,7,8]                                  *\n" +
            "*    | Output: 23                                                  *\n" +
            "*    | Explanation: The subarray [5,4,-1,7,8] has the largest      *\n" +
            "*                   sum 23.                                        *\n" +
            "*                                                                  *\n" +
            "* Constraints:                                                     *\n" +
            "*    | 1 <= nums.length <= 10^5                                    *\n" +
            "*    | -10^4 <= nums[i] <= 10^4                                    *\n" +
            "********************************************************************\n"

        println(codeChallenge)
    }

    /**
     * Resolution using Kadane's Algorithm
     */
    fun maxSubArray(nums: IntArray): Int {
        nums.printStartOfSolution()

        // Take the first element of the array as 'Max sum value so far' and 'Max sum overall'
        var maxSumSoFar = nums[0]
        var maxSumOverall = nums[0]

        // Loop through from 1 to n-1 and keep calculating the 'Max sum value so far'
        for (index in 1 until nums.size) {
            val currentElement = nums[index]
            // When the 'currentElement' is greater than 'currentElement + Max sum value so far', it is better to
            // start again, starting from the 'currentElement'
            maxSumSoFar = maxOf(currentElement, maxSumSoFar + currentElement)
            // When the 'Max sum value so far' is greater than 'Max sum overall', update it
            maxSumOverall = maxOf(maxSumOverall, maxSumSoFar)
        }

        return maxSumOverall.printEndOfSolution()
    }
}

private fun IntArray.printStartOfSolution() {
    println("====================================================================")
    val messageToPrint = StringBuilder("[")
    this.forEachIndexed { index, element ->
        messageToPrint.append(element)
        if (index < this.size - 1) {
            messageToPrint.append(",")
        }
    }
    println("- Input: nums = ${messageToPrint.append("]")}")
}

private fun Int.printEndOfSolution(): Int {
    println("- Output: $this")
    println("====================================================================")
    println()
    return this
}