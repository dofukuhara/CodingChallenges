package insertinterval

import utils.assertWith

fun main() {
    val solution = InsertInterval()

    solution.insert(
        intervals = arrayOf(intArrayOf(1,3), intArrayOf(6,9)),
        newInterval = intArrayOf(2,5)
    ).assertWith(expected = "[[1,5],[6,9]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,2), intArrayOf(7,9)),
        newInterval = intArrayOf(5,10)
    ).assertWith(expected = "[[1,2],[5,10]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,2), intArrayOf(3,5), intArrayOf(6,7)),
        newInterval = intArrayOf(4,8)
    ).assertWith(expected = "[[1,2],[3,8]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,2), intArrayOf(3,5), intArrayOf(6,10)),
        newInterval = intArrayOf(4,8)
    ).assertWith(expected = "[[1,2],[3,10]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,2), intArrayOf(3,5), intArrayOf(6,10), intArrayOf(12,14), intArrayOf(16,19)),
        newInterval = intArrayOf(4,8)
    ).assertWith(expected = "[[1,2],[3,10],[12,14],[16,19]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,2), intArrayOf(3,5), intArrayOf(6,7), intArrayOf(8,10), intArrayOf(12,16)),
        newInterval = intArrayOf(4,8)
    ).assertWith(expected = "[[1,2],[3,10],[12,16]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,5)),
        newInterval = intArrayOf(6,8)
    ).assertWith(expected = "[[1,5],[6,8]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,5)),
        newInterval = intArrayOf(0,0)
    ).assertWith(expected = "[[0,0],[1,5]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(0,0), intArrayOf(2,4), intArrayOf(9,9)),
        newInterval = intArrayOf(0,7)
    ).assertWith(expected = "[[0,7],[9,9]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(1,5), intArrayOf(6,9)),
        newInterval = intArrayOf(10,20)
    ).assertWith(expected = "[[1,5],[6,9],[10,20]]")
    solution.insert(
        intervals = arrayOf(intArrayOf(3,5), intArrayOf(12,15)),
        newInterval = intArrayOf(6,6)
    ).assertWith(expected = "[[3,5],[6,6],[12,15]]")
}
