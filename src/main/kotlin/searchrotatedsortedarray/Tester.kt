package searchrotatedsortedarray

import utils.assertWith

fun main() {
    val solution = SearchInRotatedSortedArray()

    solution.search(
        nums = intArrayOf(4,5,6,7,0,1,2),
        target = 0
    ).assertWith(4)

    solution.search(
        nums = intArrayOf(4,5,6,7,0,1,2),
        target = 3
    ).assertWith(-1)

    solution.search(
        nums = intArrayOf(1),
        target = 0
    ).assertWith(-1)

    solution.search(
        nums = intArrayOf(1,3,5),
        target = 2
    ).assertWith(-1)

    solution.search(
        nums = intArrayOf(3,1),
        target = 1
    ).assertWith(1)
}