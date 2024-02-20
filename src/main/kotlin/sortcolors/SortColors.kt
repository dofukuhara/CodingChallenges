package sortcolors

import utils.printableIntArray

class SortColor {

    init {
        val codeChallenge = "\n" +
            "*********************************** Sort Colors ***********************************\n" +
            "*                                                                                 *\n" +
            "* Given an array nums with n objects colored red, white, or blue, sort them       *\n" +
            "* 'in-place' so that objects of the same color are adjacent, with the colors in   *\n" +
            "* the order red, white, and blue.                                                 *\n" +
            "*                                                                                 *\n" +
            "* We will use the integers 0, 1, and 2 to represent the color red, white, and     *\n" +
            "* blue, respectively.                                                             *\n" +
            "*                                                                                 *\n" +
            "* You must solve this problem without using the library's sort function.          *\n" +
            "*                                                                                 *\n" +
            "* Example 1:                                                                      *\n" +
            "*    | Input: nums = [2,0,2,1,1,0]                                                *\n" +
            "*    | Output: [0,0,1,1,2,2]                                                      *\n" +
            "*                                                                                 *\n" +
            "* Example 2:                                                                      *\n" +
            "*    | Input: nums = [2,0,1]                                                      *\n" +
            "*    | Output: [0,1,2]                                                            *\n" +
            "*                                                                                 *\n" +
            "* Constraints:                                                                    *\n" +
            "*    | n == nums.length                                                           *\n" +
            "*    | 1 <= n <= 300                                                              *\n" +
            "*    | nums[i] is either 0, 1, or 2.                                              *\n" +
            "***********************************************************************************\n"
        println(codeChallenge)
    }

    // Merge Sort       | Runtime: O(n log(n))      | Memory: O(log(n)), due to the recursive call stack
    fun heapSort(nums: IntArray): IntArray {
        printSolutionStart("Heap Sort", nums)
         val heapSize = nums.size

        // Build Max Heap
        // It starts from the middle of the array and calls heapify on each element to ensure that the
        // heap is properly maintained.
        for (index in (heapSize/2 - 1) downTo 0) {
            heapify(nums, heapSize, index)
        }

        // Once the Max Heap was properly built (the root element is the highest in the array),
        // Move the head of the heap to the latest position on the array, limit its size and re-run the
        // heapification + highest value movement process
        for (heapLimiter in (heapSize -1) downTo 1) {
            // Move current root (highest in the array) to the end
            nums[0] = nums[heapLimiter].also {
                nums[heapLimiter] = nums[0]
            }

            // Call heapify on the reduced heap
            heapify(nums, heapLimiter, 0)
        }

        return nums.printSolutionEnd()
    }

    // To heapify a subtree root with node 'index' and compare its value against its children nodes left and right,
    // if present in the tree
    private fun heapify(nums: IntArray, heapSize: Int, index: Int) {
        var largest = index
        val left = 2 * index + 1
        val right = 2 * index + 2

        // If left child is larger than root
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left
        }

        // If right child is larger than largest so far
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right
        }

        // If largest is not the root element
        if (largest != index)  {
            nums[index] = nums[largest].also {
                nums[largest] = nums[index]
            }

            // Recursively heapify the affected sub-tree
            heapify(nums, heapSize, largest)
        }
    }

    // Merge Sort       | Runtime: O(n log(n)) average and worst case.      | Memory: Depends
    fun mergeSort(nums: IntArray) : IntArray {
        printSolutionStart("Merge Sort", nums)
        val result = mergeSortHelper(nums)

        return result.printSolutionEnd()
    }

    private fun mergeSortHelper(nums: IntArray) : IntArray {
        // Base case: Array with a single element, there is no need to sort, just return it
        if (nums.size <= 1) {
            return nums
        }

        val startIndex = 0
        val endIndex = nums.size - 1
        val middleIndex = (endIndex + startIndex) / 2

        // slicing the given array into two halves
        val leftArray = nums.sliceArray(startIndex..middleIndex)
        val rightArray = nums.sliceArray(middleIndex+1..endIndex)

        // Sort each half and them merge both arrays into a single one
        return merge(mergeSortHelper(leftArray), mergeSortHelper(rightArray))
    }

    private fun merge(leftArray: IntArray, rightArray: IntArray) : IntArray {
        val mergedArray = IntArray(leftArray.size + rightArray.size)

        var leftIndex = 0
        var rightIndex = 0
        var mergedIndex = 0

        // Generate mergedArray based on the values from leftArray and rightArray
        while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                mergedArray[mergedIndex] = leftArray[leftIndex]
                leftIndex++
            } else {
                mergedArray[mergedIndex] = rightArray[rightIndex]
                rightIndex++
            }
            mergedIndex++
        }

        // If rightArray has no more elements, finish the process by including any left over from leftArray
        while (leftIndex < leftArray.size) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
            mergedIndex++
        }
        // Otherwise, do the same operation but with the rightArray
        while (rightIndex < rightArray.size) {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
            mergedIndex++
        }

        // Return the merged sorted array
        return mergedArray
    }

    // Selection Sort   | Runtime: O(n2) average and worst case.            | Memory: O(1)
    fun selectionSort(nums: IntArray): IntArray {
        printSolutionStart("Selection Sort", nums)

        for (outerRunner in nums.indices) {
            // Initialize the selectionSortItem with the element at 'outerRunner' position
            var selectionSortItem = SelectionSortItem(outerRunner, nums[outerRunner])

            // starting from 'outerRunner + 1' position, check if this item is less than the one stored in selectionSortItem
            for (innerRunner in (outerRunner + 1) until nums.size) {
                if (nums[innerRunner] < selectionSortItem.value) {
                    selectionSortItem = SelectionSortItem(innerRunner, nums[innerRunner])
                }
            }

            // If the index in 'selectionSortItem' is different from 'outerRunner', it means that Selection Sort found an
            // smaller element, so let's swap them
            if (selectionSortItem.index != outerRunner) {
                nums[selectionSortItem.index] = nums[outerRunner].also {
                    nums[outerRunner] = nums[selectionSortItem.index]
                }
            }
        }
        return nums.printSolutionEnd()
    }

    // Bubble Sort      | Runtime: O(n2) average and worst case.            | Memory: O(1)
    fun bubbleSort(nums: IntArray): IntArray {
        printSolutionStart("Bubble Sort", nums)

        for (limiter in (nums.size -1) downTo 1) {
            var performedAnySwap = false
            for (runner in 1 .. limiter) {
                if (nums[runner - 1] > nums[runner]) {
                    nums[runner - 1] = nums[runner].also {
                        performedAnySwap = true
                        nums[runner] = nums[runner - 1]
                    }
                }
            }
            // If after a full traversal bubble sort didn't perform any swap operation,
            // it means that the array is already sorted, so we can early return the result.
            if (!performedAnySwap) {
                return nums.printSolutionEnd()
            }
        }
        return nums.printSolutionEnd()
    }
}

data class SelectionSortItem(
    val index: Int,
    val value: Int
)

private fun printSolutionStart(sortingAlgorithm: String, nums: IntArray) {
    println("===================================================================================")
    println("- Sorting Algorithm: $sortingAlgorithm")
    println("- Input: nums = ${nums.printableIntArray()}")
}

private fun IntArray.printSolutionEnd(): IntArray {
    println("- Output: ${this.printableIntArray()}")
    println("===================================================================================\n")
    return this
}