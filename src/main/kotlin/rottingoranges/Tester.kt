package rottingoranges

import utils.assertWith

fun main() {
    val solution = RottingOranges()

    solution.orangesRotting(grid = arrayOf(
        intArrayOf(2,1,1),
        intArrayOf(1,1,0),
        intArrayOf(0,1,1),
    )).assertWith(4)

    solution.orangesRotting(grid = arrayOf(
        intArrayOf(2,1,1),
        intArrayOf(0,1,1),
        intArrayOf(1,0,1)
    )).assertWith(-1)

    solution.orangesRotting(grid = arrayOf(
        intArrayOf(0,2)
    )).assertWith(0)

    solution.orangesRotting(grid = arrayOf(
        intArrayOf(1),
        intArrayOf(1),
        intArrayOf(1),
        intArrayOf(1)
    )).assertWith(-1)
}