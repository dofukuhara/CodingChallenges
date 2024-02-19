package permutations

import utils.printableIntArray

class Permutations {

    init {
        val codeChallenge = "\n"+
            "********************************** Permutations ***********************************\n" +
            "*                                                                                 *\n" +
            "* Given an array nums of distinct integers, return all the possible permutations. *\n" +
            "* You can return the answer in any order.                                         *\n" +
            "*                                                                                 *\n" +
            "* Example 1:                                                                      *\n" +
            "*    | Input: nums = [1,2,3]                                                      *\n" +
            "*    | Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]                  *\n" +
            "*                                                                                 *\n" +
            "* Example 2:                                                                      *\n" +
            "*    | Input: nums = [0,1]                                                        *\n" +
            "*    | Output: [[0,1],[1,0]]                                                      *\n" +
            "*                                                                                 *\n" +
            "* Example 3:                                                                      *\n" +
            "*    | Input: nums = [1]                                                          *\n" +
            "*    | Output: [[1]]                                                              *\n" +
            "*                                                                                 *\n" +
            "* Constraints:                                                                    *\n" +
            "*    | 1 <= nums.length <= 6                                                      *\n" +
            "*    | -10 <= nums[i] <= 10                                                       *\n" +
            "*    | All the integers of nums are unique.                                       *\n" +
            "***********************************************************************************\n"

        println(codeChallenge)
    }

    fun permute(nums: IntArray): List<List<Int>> {
        printSolutionStart(nums)

        val result = mutableListOf<List<Int>>()
        permuteBacktracking(nums, mutableListOf(), result)
        return result.printSolutionEnd()
    }

    private fun permuteBacktracking(nums: IntArray, tempList: MutableList<Int>, result: MutableList<List<Int>>) {
        // If the item in the temp list has the size of the original array, them include a copy of it in the result array and return
        if (tempList.size == nums.size) {
            result.add(tempList.toList())
            return
        }

        for (number in nums) {
            // Run only if the current number is not yet set in the temp list (nums have only distinct integers)
            if (!tempList.contains(number)) {
                // Add this current number at the end of the temp list
                tempList.add(number)
                // Recursively run backTrack to continue generating the next element of the permutation
                permuteBacktracking(nums, tempList, result)
                // After a new permutation was included in the result list, remove this number from temp list to continue exploring other possibilities
                tempList.remove(number)
            }
        }
    }

    fun permuteDp(nums: IntArray): List<List<Int>> {
        printSolutionStart(nums)

        // Initialize DP cache
        val dp = Array<MutableList<List<Int>>>(nums.size) { mutableListOf() }
        // Set the first item of the cache
        dp[0] = mutableListOf(listOf(nums[0]))

        // Initiate DP
        for (dpProgression in 1 until nums.size) {
            // Get the cached result from previous iteration
            val previousResult = dp[dpProgression-1]

            // For every past combinations
            previousResult.forEach { subList ->
                // Create a new list, with the current item + all items from previous iteration
                val permutation = mutableListOf<Int>(nums[dpProgression])
                permutation.addAll(subList)

                // Add a copy of it (.toList()), as we are going to update the values from 'permutation' mutableList
                dp[dpProgression].add(permutation.toList())
                for (index in 1 until permutation.size) {
                    // Loop to move the newly item, from the first position until the last
                    permutation[index] = permutation[index -1].also {
                        permutation[index -1] = permutation[index]
                    }
                    // And save a copy of it
                    dp[dpProgression].add(permutation.toList())
                }
            }
        }

        // Return the result - last item from the DP cache
        return dp[nums.size - 1].printSolutionEnd()
    }
}

private fun printSolutionStart(nums: IntArray) {
    println("===================================================================================")
    println("- Input: nums = ${nums.printableIntArray()}")
}

private fun List<List<Int>>.printSolutionEnd(): List<List<Int>> {
    println("- Output: $this")
    println("===================================================================================\n")
    return this
}