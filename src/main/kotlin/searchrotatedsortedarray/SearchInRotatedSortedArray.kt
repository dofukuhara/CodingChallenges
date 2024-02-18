package searchrotatedsortedarray

import utils.printableIntArray

class SearchInRotatedSortedArray {
    init {
        val codeChallenge = "\n" +
            "************* Search In Rotated Sorted Array *************\n" +
            "*                                                        *\n" +
            "* There is an integer array nums sorted in ascending     *\n" +
            "* order (with distinct values).                          *\n" +
            "*                                                        *\n" +
            "* Prior to being passed to your function, nums is        *\n" +
            "* possibly rotated at an unknown pivot index k (1 <= k < *\n" +
            "* nums.length) such that the resulting array is          *\n" +
            "* [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], *\n" +
            "* ..., nums[k-1]] (0-indexed). For example,              *\n" +
            "* [0,1,2,4,5,6,7] might be rotated at pivot index 3 and  *\n" +
            "* become [4,5,6,7,0,1,2].                                *\n" +
            "*                                                        *\n" +
            "* Given the array nums after the possible rotation and   *\n" +
            "* an integer target, return the index of target if it is *\n" +
            "* in nums, or -1 if it is not in nums.                   *\n" +
            "*                                                        *\n" +
            "* You must write an algorithm with O(log n) runtime      *\n" +
            "* complexity.                                            *\n" +
            "*                                                        *\n" +
            "* Example 1:                                             *\n" +
            "*    | Input: nums = [4,5,6,7,0,1,2], target = 0         *\n" +
            "*    | Output: 4                                         *\n" +
            "*                                                        *\n" +
            "* Example 2:                                             *\n" +
            "*    | Input: nums = [4,5,6,7,0,1,2], target = 3         *\n" +
            "*    | Output: -1                                        *\n" +
            "*                                                        *\n" +
            "* Example 3:                                             *\n" +
            "*    | Input: nums = [1], target = 0                     *\n" +
            "*    | Output: -1                                        *\n" +
            "*                                                        *\n" +
            "* Constraints:                                           *\n" +
            "*    | 1 <= nums.length <= 5000                          *\n" +
            "*    | -10^4 <= nums[i] <= 10^4                          *\n" +
            "*    | All values of nums are unique.                    *\n" +
            "*    | nums is an ascending array that is possibly       *\n" +
            "*        rotated.                                        *\n" +
            "*    | -10^4 <= target <= 10^4                           *\n" +
            "**********************************************************\n"
        println(codeChallenge)
    }

    fun search(nums: IntArray, target: Int): Int {
        printSolutionStart(nums, target)

        // Edge case: Only one element in the array
        if (nums.size == 1) {
            if (nums[0] == target) {
                return 0.printSolutionEnd()
            } else {
                return (-1).printSolutionEnd()
            }
        }

        if (nums[nums.size - 1] > nums[0]) {
            // Array is properly sorted
            return returnIndexIfFound(nums, target, 0, nums.size -1).printSolutionEnd()
        } else {
            // Find the pivot
            val pivotIndex = findPivotIndex(nums)
            if (pivotIndex == -1) return (-1).printSolutionEnd()

            val firstNumber = nums[0]                       // First number on the array
            val numberAtPivot = nums[pivotIndex]            // Highest number on the array
            val numberAfterPivot = nums[pivotIndex + 1]     // Lowest number on the array
            val lastNumber = nums[nums.size -1]             // Last number on the array

            /*
                We have two sorted arrays, in this case they will be:
                    nums[0] until nums[pivot]
                    nums[pivot+1] until nums[size-1]
                So, we need to check in which array we wil be performing the search
             */

            if (target >= firstNumber && target <= numberAtPivot) {
                // Compare from 'startIndex' until 'pivot'
                return returnIndexIfFound(nums, target, 0, pivotIndex).printSolutionEnd()
            } else if (target >= numberAfterPivot && target <= lastNumber) {
                // Compare from 'pivot + 1' until 'endIndex'
                return returnIndexIfFound(nums, target, pivotIndex + 1, nums.size -1).printSolutionEnd()
            } else {
                // Else target could be either greater than the higher number in this array or less than the lowest number in this array
                return (-1).printSolutionEnd()
            }
        }
    }

    private fun findPivotIndex(nums: IntArray): Int {
        var startIndex = 0
        var endIndex = nums.size - 1

        while (startIndex < endIndex) {
            val pivotIndex = ((endIndex - startIndex) / 2) + startIndex
            if (nums[pivotIndex+1] < nums[pivotIndex]) {
                // If the number after the pivot is less than the one in the pivot, then it is the actual pivot point
                return pivotIndex
            } else if (nums[pivotIndex] > nums[startIndex]) {
                startIndex = pivotIndex
            } else {
                endIndex = pivotIndex
            }
        }
        return -1
    }

    private fun returnIndexIfFound(nums: IntArray, target: Int, startIndex: Int, endIndex: Int): Int {
        // When startIndex is greater than endIndex, we have exhausted the possibilities and didn't find the index, so returning -1
        if (startIndex > endIndex) return -1

        val middle = ((endIndex - startIndex) / 2) + startIndex
        return when {
            (nums[startIndex] == target) ->  startIndex
            (nums[middle] == target) -> middle
            (nums[endIndex] == target) -> endIndex
            (target > nums[startIndex] && target < nums[middle]) -> returnIndexIfFound(nums, target, startIndex+1, middle-1)
            (target > nums[middle] && target < nums[endIndex]) -> returnIndexIfFound(nums, target, middle+1, endIndex-1)
            else -> -1
        }
    }
}

private fun printSolutionStart(nums: IntArray, target: Int) {
    println("==========================================================")
    println("- Input: nums = ${nums.printableIntArray()}, target = $target")
}

private fun Int.printSolutionEnd(): Int {
    println("- Output: $this")
    println("==========================================================\n")
    return this
}