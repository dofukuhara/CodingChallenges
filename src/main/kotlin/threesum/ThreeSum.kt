package threesum

import utils.printableIntArray

class ThreeSum {
    init {
        val codeChallenge = "\n" +
                "************************* 3 Sum ***************************\n" +
                "* Given an integer array nums, return all the triplets    *\n" +
                "* [nums[i], nums[j], nums[k]] such that i != j, i != k,   *\n" +
                "* and j != k, and nums[i] + nums[j] + nums[k] == 0.       *\n" +
                "*                                                         *\n" +
                "* Notice that the solution set must not contain           *\n" +
                "* duplicate triplets.                                     *\n" +
                "*                                                         *\n" +
                "* Example 1:                                              *\n" +
                "*    | Input: nums = [-1,0,1,2,-1,-4]                     *\n" +
                "*    | Output: [[-1,-1,2],[-1,0,1]]                       *\n" +
                "*    | Explanation:                                       *\n" +
                "*      nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.    *\n" +
                "*      nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.    *\n" +
                "*      nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0. *\n" +
                "*      The distinct triplets are [-1,0,1] and [-1,-1,2].  *\n" +
                "*      Notice that the order of the output and the order  *\n" +
                "*      of the triplets does not matter.                   *\n" +
                "*                                                         *\n" +
                "* Example 2:                                              *\n" +
                "*    | Input: nums = [0,1,1]                              *\n" +
                "*    | Output: []                                         *\n" +
                "*    | Explanation:                                       *\n" +
                "*      The only possible triplet does not sum up to 0.    *\n" +
                "*                                                         *\n" +
                "* Example 3:                                              *\n" +
                "*    | Input: nums = [0,0,0]                              *\n" +
                "*    | Output: [[0,0,0]]                                  *\n" +
                "*    | Explanation:                                       *\n" +
                "*      The only possible triplet sums up to 0.            *\n" +
                "*                                                         *\n" +
                "* Constraints:                                            *\n" +
                "*    | 3 <= nums.length <= 3000                           *\n" +
                "*    | -10^5 <= nums[i] <= 10^5                           *\n" +
                "***********************************************************\n"

        println(codeChallenge)
    }
    fun threeSum(nums: IntArray): List<List<Int>> {
        printSolutionStart(nums)

        nums.sort()
        // Edge cases (after sorting the array) - No possible triple when:
        // 1) nums size is less than or equal to 2
        // 2) All items from array are greater than zero;
        // 3) Up to 2 zeros at the start of the array and the remaining items are all greater than zero;
        // 4) All items from array are less than zero;
        // 5) Up to 2 zeros at the end of the array and the remaining items are all less than zero.
        if (nums.size <= 2 ||
            nums.first() > 0 && nums.last() > 0 ||
            nums.first() == 0 && nums[2] > 0 ||
            nums.first() < 0 && nums.last() < 0 ||
            nums[nums.size-3] < 0 && nums.last() == 0) {
            return emptyList<List<Int>>().printSolutionEnd()
        }

        // Edge case: When all items in the array are zero, just return one triple of zeros.
        if (nums.count { it == 0 } == nums.size) {
            return listOf(listOf(0,0,0)).printSolutionEnd()
        }

        val negatives = mutableMapOf<Int,Int>()
        val zeros = nums.count { it == 0 }
        val positives = mutableMapOf<Int,Int>() // Using a Set to ensure only unique triples in the result

        nums.forEach {
            if (it < 0) {
                negatives[it] = negatives[it]?.plus(1) ?: 1
            } else if (it > 0) {
                positives[it] = positives[it]?.plus(1) ?: 1
            }
        }

        val resultList = mutableSetOf<List<Int>>()

        // Case 1 of 3: Only zeros combination.
        if (zeros >= 3) {
            resultList.add(listOf(0,0,0))
        }

        // Case 2 of 3: one negative with one zero with one positive
        if (zeros > 0) {
            negatives.forEach { (negativeNumber, negativeCounter) ->
                val positiveNumber = -1 * negativeNumber
                if (positives.contains(positiveNumber)) {
                    val positiveCounter = positives[positiveNumber] ?: 1
                    val possibleCombinations = minOf(
                        minOf(negativeCounter, positiveCounter),
                        zeros
                    )
                    resultList.addAll(
                        Array(size = possibleCombinations) {
                            listOf(negativeNumber,0,positiveNumber)
                        }.toList()
                    )
                }
            }
        }

        // Case 3 of 3: two negatives with on positive -or- one negative with two positives
        positives.forEach { (positiveNumber, positiveCounter) ->
            negatives.forEach { (negativeNumber, negativeCounter) ->
                val numberNeeded = (positiveNumber + negativeNumber) * -1
                // same negative number is needed
                if (numberNeeded == negativeNumber) {
                    if (negativeCounter > 1) {
                        val possibleCombinations = minOf(
                            positiveCounter,
                            negativeCounter/2
                        )
                        resultList.addAll(
                            Array(size = possibleCombinations) {
                                listOf(negativeNumber,negativeNumber,positiveNumber)
                            }.toList()
                        )
                    }
                // same positive number is needed
                } else if (numberNeeded == positiveNumber) {
                    if (positiveCounter > 1) {
                        val possibleCombinations = minOf(
                            positiveCounter/2,
                            negativeCounter
                        )
                        resultList.addAll(
                            Array(size = possibleCombinations) {
                                listOf(negativeNumber,positiveNumber,positiveNumber)
                            }.toList()
                        )
                    }
                // two different negatives and one positive
                } else if (numberNeeded < 0) {
                    val negativeNeededCounter = negatives[numberNeeded]
                    negativeNeededCounter?.let {
                        val possibleCombinations = minOf(
                            minOf(positiveCounter, negativeCounter),
                            it
                        )
                        resultList.addAll(
                            Array(size = possibleCombinations) {
                                listOf(
                                    minOf(negativeNumber, numberNeeded),
                                    maxOf(negativeNumber, numberNeeded),
                                    positiveNumber)
                            }.toList()
                        )
                    }
                // two different positives and one negative
                } else if (numberNeeded > 0) {
                    val positiveNeededCounter = positives[numberNeeded]
                    positiveNeededCounter?.let {
                        val possibleCombinations = minOf(
                            minOf(positiveCounter, negativeCounter),
                            it
                        )
                        resultList.addAll(
                            Array(size = possibleCombinations) {
                                listOf(
                                    negativeNumber,
                                    minOf(positiveNumber, numberNeeded),
                                    maxOf(positiveNumber, numberNeeded)
                                )
                            }.toList()
                        )
                    }
                }
            }
        }

        return resultList.toList().printSolutionEnd()
    }
}

private fun printSolutionStart(nums: IntArray) {
    println("===========================================================")
    println("- Input: nums = ${nums.printableIntArray()}")
}

private fun List<List<Int>>.printSolutionEnd(): List<List<Int>> {
    println("- Output: $this")
    println("===========================================================\n")
    return this
}