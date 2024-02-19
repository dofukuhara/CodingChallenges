package permutations

import utils.assertWith

fun main() {
    val solution = Permutations()

    /*
        Solution implemented with 'Backstack recursion' approach
     */
    solution.permute(
        nums = intArrayOf(1,2,3)
    ).assertWith("[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]")
    solution.permute(
        nums = intArrayOf(0,1)
    ).assertWith("[[0, 1], [1, 0]]")
    solution.permute(
        nums = intArrayOf(1)
    ).assertWith("[[1]]")

    /*
        Solution implemented with 'Dynamic Programming' approach
     */
    solution.permuteDp(
        nums = intArrayOf(1,2,3)
    ).assertWith("[[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]")
    solution.permuteDp(
        nums = intArrayOf(0,1)
    ).assertWith("[[1, 0], [0, 1]]")
    solution.permuteDp(
        nums = intArrayOf(1)
    ).assertWith("[[1]]")
}