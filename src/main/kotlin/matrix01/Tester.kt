package matrix01

import utils.assertWith

fun main() {

    val solution = Matrix01()

    solution.updateMatrix(arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,1,0),
        intArrayOf(0,0,0)
    )).assertWith("[[0,0,0],[0,1,0],[0,0,0]]")

    solution.updateMatrix(arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,1,0),
        intArrayOf(1,1,1)
    )).assertWith("[[0,0,0],[0,1,0],[1,2,1]]")

    solution.updateMatrix(arrayOf(
        intArrayOf(0,0,1,0,1,1,1,0,1,1),
        intArrayOf(1,1,1,1,0,1,1,1,1,1),
        intArrayOf(1,1,1,1,1,0,0,0,1,1),
        intArrayOf(1,0,1,0,1,1,1,0,1,1),
        intArrayOf(0,0,1,1,1,0,1,1,1,1),
        intArrayOf(1,0,1,1,1,1,1,1,1,1),
        intArrayOf(1,1,1,1,0,1,0,1,0,1),
        intArrayOf(0,1,0,0,0,1,0,0,1,1),
        intArrayOf(1,1,1,0,1,1,0,1,0,1),
        intArrayOf(1,0,1,1,1,0,1,1,1,0)
    )).assertWith(
        "[" +
            "[0,0,1,0,1,2,1,0,1,2]," +
            "[1,1,2,1,0,1,1,1,2,3]," +
            "[2,1,2,1,1,0,0,0,1,2]," +
            "[1,0,1,0,1,1,1,0,1,2]," +
            "[0,0,1,1,1,0,1,1,2,3]," +
            "[1,0,1,2,1,1,1,2,1,2]," +
            "[1,1,1,1,0,1,0,1,0,1]," +
            "[0,1,0,0,0,1,0,0,1,2]," +
            "[1,1,1,0,1,1,0,1,0,1]," +
            "[1,0,1,1,1,0,1,2,1,0]" +
        "]"
    )
}