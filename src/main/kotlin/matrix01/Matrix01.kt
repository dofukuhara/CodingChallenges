package matrix01

import utils.printableArrayOfIntArray
import kotlin.collections.ArrayDeque

class Matrix01 {

    init {
        val codeChallenge = "\n" +
            "******************** 01 Matrix ********************\n" +
            "* Given an m x n binary matrix mat, return the    *\n" +
            "* distance of the nearest 0 for each cell.        *\n" +
            "*                                                 *\n" +
            "* The distance between two adjacent cells is 1.   *\n" +
            "*                                                 *\n" +
            "* Example 1:                                      *\n" +
            "*    | Input: mat = [[0,0,0],[0,1,0],[0,0,0]]     *\n" +
            "*    | Output: [[0,0,0],[0,1,0],[0,0,0]]          *\n" +
            "*                                                 *\n" +
            "* Example 2:                                      *\n" +
            "*    | Input: mat = [[0,0,0],[0,1,0],[1,1,1]]     *\n" +
            "*    | Output: [[0,0,0],[0,1,0],[1,2,1]]          *\n" +
            "*                                                 *\n" +
            "* Constraints:                                    *\n" +
            "*    | m == mat.length                            *\n" +
            "*    | n == mat[i].length                         *\n" +
            "*    | 1 <= m, n <= 10^4                          *\n" +
            "*    | 1 <= m * n <= 10^4                         *\n" +
            "*    | mat[i][j] is either 0 or 1.                *\n" +
            "*    | There is at least one 0 in mat.            *\n" +
            "***************************************************\n"

        println(codeChallenge)
    }

    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        mat.printSolutionStart()
        // Edge case: Handling empty matrix
        if (mat.isEmpty()) return mat

        /*
            We are performing 2 actions in this loop:
            1) As we are going to re-use this matrix 'mat' as a solution, when it's cell value is '1', them this value
               is re-mapped to Int.MAX_VALUE, so that we can perform some calculation for this cell;
            2) We will create a queue that, initially will be filled with all the positions for the '0' elements, and it
               will be used as the starting point of the calculation
         */
        val bfsQueue = ArrayDeque<Position>()
        for (row in mat.indices) {
            for (col in mat[0].indices) {
                if (mat[row][col] == 1) {
                    mat[row][col] = Int.MAX_VALUE
                } else {
                    bfsQueue.add(Position(row, col))
                }
            }
        }

        // Starting of BFS run
        while (bfsQueue.isNotEmpty()) {
            val cellToAnalyze = bfsQueue.removeFirst()
            // Let's check all the neighbours (Top, Right, Bottom and Left) of the given cell
            for (neighbour in cellToAnalyze.neighbours()) {
                if (neighbour.isNotOutOfMatrixBounds(mat)) {
                    /*
                        If the neighbour's value is greater than the current cell being analyzed + 1 (remember that we
                        replaced all the '1's in the matrix by 'Int.MAX_VALUE'?), then we will propagate this new
                        distance to this cell, and will add this neighbour position to the bfsQueue, so that we can
                        perform the same check against it's neighbours
                     */
                    if (neighbour.getValue(mat) > cellToAnalyze.getValue(mat) + 1) {
                        bfsQueue.add(neighbour)
                        neighbour.setValue(mat, cellToAnalyze.getValue(mat) + 1)
                    }
                }
            }
        }

        return mat.printSolutionEnd()
    }
}

// Data class that defines a cell position
data class Position(
    val row: Int, val col: Int
) {
    fun getValue(matrix: Array<IntArray>): Int = matrix[row][col]
    fun setValue(matrix: Array<IntArray>, value: Int) {
        matrix[row][col] = value
    }

    // Checking if the given cell is not out of the Matrix boundaries
    fun isNotOutOfMatrixBounds(matrix: Array<IntArray>): Boolean =
        row in matrix.indices && col in matrix[0].indices
}

// Defining the neighbours of the given cell that will be visited during BFS
private fun Position.neighbours(): List<Position> = listOf(
    Position(row = row - 1, col = col),     // Top
    Position(row = row,     col = col + 1), // Right
    Position(row = row + 1, col = col),     // Bottom
    Position(row = row,     col = col - 1)  //Left
)

private fun Array<IntArray>.printSolutionStart(): Array<IntArray> {
    println("===================================================")
    println("- Input: mat = ${this.printableArrayOfIntArray()}")
    return this
}

private fun Array<IntArray>.printSolutionEnd(): Array<IntArray> {
    println("- Output: ${this.printableArrayOfIntArray()}")
    println("===================================================\n")
    return this
}