package combinationsum

import utils.printableIntArray

class CombinationSum {

    init {
        val codeChallenge = "\n" +
            "******************** Combination Sum ********************\n" +
            "*                                                       *\n" +
            "* Given an array of distinct integers candidates and a  *\n" +
            "* target integer target, return a list of all unique    *\n" +
            "* combinations of candidates where the chosen numbers   *\n" +
            "* sum to target. You may return the combinations in any *\n" +
            "* order.                                                *\n" +
            "*                                                       *\n" +
            "* The same number may be chosen from candidates an      *\n" +
            "* unlimited number of times. Two combinations are       *\n" +
            "* unique if the frequency of at least one of the chosen *\n" +
            "* numbers is different.                                 *\n" +
            "*                                                       *\n" +
            "* The test cases are generated such that the number of  *\n" +
            "* unique combinations that sum up to target is less     *\n" +
            "* than 150 combinations for the given input.            *\n" +
            "*                                                       *\n" +
            "* Example 1:                                            *\n" +
            "*    | Input: candidates = [2,3,6,7], target = 7        *\n" +
            "*    | Output: [[2,2,3],[7]]                            *\n" +
            "*    | Explanation:                                     *\n" +
            "*            2 and 3 are candidates, and 2 + 2 + 3 = 7. *\n" +
            "*            Note that 2 can be used multiple times.    *\n" +
            "*            7 is a candidate, and 7 = 7.               *\n" +
            "*            These are the only two combinations.       *\n" +
            "*                                                       *\n" +
            "* Example 2:                                            *\n" +
            "*    | Input: candidates = [2,3,5], target = 8          *\n" +
            "*    | Output: [[2,2,2,2],[2,3,3],[3,5]]                *\n" +
            "*                                                       *\n" +
            "* Example 3:                                            *\n" +
            "*    | Input: candidates = [2], target = 1              *\n" +
            "*    | Output: []                                       *\n" +
            "*                                                       *\n" +
            "* Constraints:                                          *\n" +
            "*    | 1 <= candidates.length <= 30                     *\n" +
            "*    | 2 <= candidates[i] <= 40                         *\n" +
            "*    | All elements of candidates are distinct.         *\n" +
            "*    | 1 <= target <= 40                                *\n" +
            "*********************************************************\n"

        println(codeChallenge)
    }
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        printSolutionStart(candidates, target)

        val result = mutableListOf<List<Int>>()
        // Sort candidates for better performance in the backtrack run
        candidates.sort()
        sumBackTracking(
            candidates = candidates,
            lookupStart = 0,
            target = target,
            tempSum = 0,
            tempList = mutableListOf(),
            result = result)
        return result.printSolutionEnd()
    }

    private fun sumBackTracking(candidates: IntArray, lookupStart: Int, target: Int, tempSum: Int, tempList: MutableList<Int>, result: MutableList<List<Int>>) {
        // Stop condition - when tempSum is greater or equal target
        if (tempSum >= target) {
            // But will only store the result if tempSum is equal to target
            if (tempSum == target) {
                result.add(tempList.toList())
            }
            return
        }

        // Combination generation loop
        for (index in lookupStart until candidates.size) {
            // Add current element in the list
            tempList.add(candidates[index])
            // Run combination generation recursion, but it can start from current index
            sumBackTracking(candidates, index, target, (tempSum + candidates[index]), tempList, result)
            // When a possible combination was generated, remove the current number and proceed with other possibilities
            tempList.removeLast()
        }
    }

    fun combinationSumDp(candidates: IntArray, target: Int): List<List<Int>> {
        printSolutionStart(candidates, target)

        // Array used in our DP, caching values from 0 until target
        val dp = Array<MutableList<List<Int>>>(target + 1) { mutableListOf() }

        // Progression traversal, where the future values will consume cached values from previous operations
        for (dpProgression in 1 .. target) {
            for (candidate in candidates) {
                val leftOver = dpProgression - candidate
                if (leftOver == 0) {
                    // if the current value is same as the candidate, just add it to the list
                    dp[dpProgression].add(mutableListOf(candidate))
                } else if (leftOver > 0) {
                    dp[leftOver].forEach {
                        // If a valid leftOver number is found, then combine the current 'candidate' with the combination from the leftOver
                        // (but only if it form a unique combination -> to avoid including [1,2,1] and [1,1,2])
                        val newList = (it + listOf(candidate)).sorted()
                        if (!dp[dpProgression].contains(newList)) {
                            dp[dpProgression].add(newList)
                        }
                    }
                }
            }
        }

        // Lastly, return the list of possible combinations for the target value (last element from the DP cache)
        return dp[target].printSolutionEnd()
    }
}

private fun printSolutionStart(candidates: IntArray, target: Int) {
    println("=========================================================")
    println("- Input: candidates = ${candidates.printableIntArray()}, target = $target")
}

private fun List<List<Int>>.printSolutionEnd():List<List<Int>> {
    println("- Output: $this")
    println("=========================================================\n")
    return this
}