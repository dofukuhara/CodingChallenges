package insertinterval

import utils.printableArrayOfIntArray
import utils.printableIntArray

class InsertInterval {

    init {
        val codeChallenge =
            "\n" +
            "********************** Insert Interval **********************\n" +
            "* You are given an array of non-overlapping intervals       *\n" +
            "* intervals where intervals[i] = [start, end] represent     *\n" +
            "* the start and the end of the ith interval and intervals   *\n" +
            "* is sorted in ascending order by start. You are also given *\n" +
            "* an interval newInterval = [start, end] that represents the*\n" +
            "* start and end of another interval.                        *\n" +
            "*                                                           *\n" +
            "* Insert newInterval into intervals such that intervals is  *\n" +
            "* still sorted in ascending order by start and intervals    *\n" +
            "* still does not have any overlapping intervals (merge      *\n" +
            "* overlapping intervals if necessary).                      *\n" +
            "*                                                           *\n" +
            "* Return intervals after the insertion.                     *\n" +
            "*                                                           *\n" +
            "* Example 1:                                                *\n" +
            "*    | Input: intervals = [[1,3],[6,9]], newInterval = [2,5]*\n" +
            "*    | Output: [[1,5],[6,9]]                                *\n" +
            "*                                                           *\n" +
            "* Example 2:                                                *\n" +
            "*    | Input: intervals = [[1,2],[3,5],[6,7],[8,10],        *\n" +
            "*             [12,16]], newInterval = [4,8]                 *\n" +
            "*    | Output: [[1,2],[3,10],[12,16]]                       *\n" +
            "*    | Explanation: Because the new interval [4,8] overlaps *\n" +
            "*                   with [3,5],[6,7],[8,10].                *\n" +
            "*                                                           *\n" +
            "* Constraints:                                              *\n" +
            "*    | 0 <= intervals.length <= 10^4                        *\n" +
            "*    | intervals[i].length == 2                             *\n" +
            "*    | 0 <= start <= end <= 105                             *\n" +
            "*    | intervals is sorted by start in ascending order.     *\n" +
            "*    | newInterval.length == 2                              *\n" +
            "*    | 0 <= start <= end <= 10^5                            *\n" +
            "*************************************************************\n"

        println(codeChallenge)
    }

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        printSolutionStart(intervals, newInterval)

        // Edge cases handling
        // Case 1: intervals array is empty
        // Case 2: entire newInterval is less than the start of the first interval
        // Case 3: entire newInterval is greater than the end of the last interval
        if (intervals.isEmpty()) {
            return arrayOf(newInterval).printEndSolution()
        } else if (newInterval[1] < intervals[0][0]) {
            return arrayOf(newInterval).plus(intervals).printEndSolution()
        } else if (newInterval[0] > intervals[intervals.size-1][1]) {
            return intervals.plus(newInterval).printEndSolution()
        }

        val newIntervalStart = newInterval[0]
        val newIntervalEnd = newInterval[1]

        var foundNewStartIndex = false
        var foundNewEndIndex = false
        var storedStartInterval = 0
        val resultArray = mutableListOf<IntArray>()

        for (index in intervals.indices) {
            if (!foundNewStartIndex) {
                // Condition to find the interval where newIntervalStart fits
                if (intervals[index][0] >= newIntervalStart || newIntervalStart <= intervals[index][1]) {
                    foundNewStartIndex = true

                    if (newIntervalEnd < intervals[index][0]) {
                        // Case 1:    The new end interval is less than the current start of the interval
                        // Behaviour: As the entire newInterval is less than the current interval, we can add
                        //            'newInterval' followed by the current interval in the resultArray and mark
                        //            it as completed
                        resultArray.add(newInterval)
                        resultArray.add(intervals[index])
                        foundNewStartIndex = true
                        foundNewEndIndex = true
                    } else if (index == intervals.size - 1 || newIntervalEnd < intervals[index+1][0]) {
                        // Case 2:    This is the last element of the Array -or-
                        // Case 3:    There is a next element in the Array, but the new interval end value is less than
                        //            the start of the next
                        // Behaviour: It is possible to calculate the new start of the interval, as well as the end
                        //            value of the interval
                        resultArray.add(intArrayOf(
                            minOf(newIntervalStart, intervals[index][0]),
                            maxOf(newIntervalEnd, intervals[index][1])
                        ))
                        foundNewEndIndex = true
                    } else {
                        // Case 4:    There is a next element in the Array and the new end interval overlaps wit the
                        //            following element
                        // Behaviour: As it is not possible to determine the end interval, save the new start date until
                        //            a place to fit the new end interval
                        storedStartInterval = minOf(newIntervalStart, intervals[index][0])
                    }
                } else {
                    resultArray.add(intervals[index])
                }
            } else if (!foundNewEndIndex) {
                if (
                    index == intervals.size - 1 ||
                    newIntervalEnd <= intervals[index][1] ||
                    newIntervalEnd < intervals[index+1][0]
                    ) {
                    // Case 1:    There is no more elements in the array -or-
                    // Case 2:    The new end interval is less or equal than the current end -or-
                    // Case 3:    The new end interval is less than the start of the next interval
                    // Behaviour: It is possible to calculate the end value of the interval (and use the stored start value)
                    resultArray.add(intArrayOf(
                        storedStartInterval,
                        maxOf(newIntervalEnd, intervals[index][1])
                    ))
                    foundNewEndIndex = true
                }
                // Case 4: If cases 1 and 2 are not meet, it means that new end interval overlaps this entire interval,
                //         so we can just skip to the next validation
            } else {
                // At this point, the new start and end intervals where properly fitted in previous intervals, so just
                // copy this interval in the result array
                resultArray.add(intervals[index])
            }
        }

        if (!foundNewStartIndex) {
            // If after traversing all the interval array and the start of newInterval couldn't be fitted, it means
            // that this new start interval is greater than the last end interval, so newInterval can be added at
            // the end of the resultArray
            resultArray.add(newInterval)
        }
        return resultArray.toTypedArray().printEndSolution()
    }
}

private fun printSolutionStart(intervals: Array<IntArray>, newInterval: IntArray) {
    println("=============================================================")
    println("- Input:\n" +
            "     intervals = ${intervals.printableArrayOfIntArray()}\n" +
            "     newInterval = ${newInterval.printableIntArray()}")
}

private fun Array<IntArray>.printEndSolution():Array<IntArray> {
    println("- Output: ${this.printableArrayOfIntArray()}")
    println("=============================================================\n")
    return this
}