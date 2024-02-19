package combinationsum

import utils.assertWith

fun main() {
    val solution = CombinationSum()

    /*
        Solution implemented with 'Backstack recursion' approach
     */
    solution.combinationSum(
        candidates = intArrayOf(2,3,6,7),
        target = 7
    ).assertWith("[[2, 2, 3], [7]]")

    solution.combinationSum(
        candidates = intArrayOf(2,3,5),
        target = 8
    ).assertWith("[[2, 2, 2, 2], [2, 3, 3], [3, 5]]")

    solution.combinationSum(
        candidates = intArrayOf(2),
        target = 1
    ).assertWith("[]")

    /*
        Solution implemented with 'Dynamic Programming' approach
     */
    solution.combinationSumDp(
        candidates = intArrayOf(2,3,6,7),
        target = 7
    ).assertWith("[[2, 2, 3], [7]]")

    solution.combinationSumDp(
        candidates = intArrayOf(2,3,5),
        target = 8
    ).assertWith("[[2, 2, 2, 2], [2, 3, 3], [3, 5]]")

    solution.combinationSumDp(
        candidates = intArrayOf(2),
        target = 1
    ).assertWith("[]")
}