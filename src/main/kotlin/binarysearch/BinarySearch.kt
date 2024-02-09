package binarysearch

import utils.printableIntArray

class BinarySearch {
    init {
        val codeChallenge = "\n" +
            "********************** Binary Search **********************\n" +
            "* Given an array of integers nums which is sorted in      *\n" +
            "* ascending order, and an integer target, write a function*\n" +
            "* to search target in nums. If target exists, then return *\n" +
            "* its index. Otherwise, return -1.                        *\n" +
            "*                                                         *\n" +
            "* You must write an algorithm with O(log n) runtime       *\n" +
            "* complexity.                                             *\n" +
            "*                                                         *\n" +
            "* Example 1:                                              *\n" +
            "*    | Input: nums = [-1,0,3,5,9,12], target = 9          *\n" +
            "*    | Output: 4                                          *\n" +
            "*    | Explanation: 9 exists in nums and its index is 4   *\n" +
            "* Example 2:                                              *\n" +
            "*    | Input: nums = [-1,0,3,5,9,12], target = 2          *\n" +
            "*    | Output: -1                                         *\n" +
            "*    | Explanation: 2 does not exist in nums so return -1 *\n" +
            "***********************************************************\n"

        println(codeChallenge)
    }

    fun search(nums: IntArray, target: Int): Int {
        printSolutionStart(nums, target)
        return binarySearch(nums = nums, target = target, start = 0, end = nums.size - 1).printSolutionEnd()
    }

    private fun binarySearch(nums: IntArray, target: Int, start: Int, end: Int): Int {
        // When the recursion tries to reach an out-of-bounds part of the array, return -1
        if (start > end) {
            return -1
        }

        val middle = start + (end - start)/2
        // Case 1: Target found, return its index
        // Case 2: Target is less than current value, so traverse to the left side of the tree
        // Case 3: Target is greater than current value, so traverse to the right side of the tree
        return when {
            nums[middle] == target -> middle
            nums[middle] > target -> binarySearch(nums = nums, target = target, start = start, end = middle - 1)
            else -> binarySearch(nums = nums, target = target, start = middle + 1, end = end)
        }
    }
}

private fun printSolutionStart(nums: IntArray, target: Int) {
    println("===========================================================")
    println("- Input: nums = ${nums.printableIntArray()}, target = $target")
}

private fun Int.printSolutionEnd(): Int {
    println("- Output: $this")
    println("===========================================================\n")
    return this
}