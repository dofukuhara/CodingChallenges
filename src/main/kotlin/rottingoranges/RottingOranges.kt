package rottingoranges

import utils.printableArrayOfIntArray
import java.util.*

class RottingOranges {
    init {
        val codeChallenge = "\n" +
            "********************* Rotting Oranges *********************\n" +
            "*                                                         *\n" +
            "* You are given an m x n grid where each cell can have    *\n" +
            "* one of three values:                                    *\n" +
            "*                                                         *\n" +
            "* -> 0 representing an empty cell,                        *\n" +
            "* -> 1 representing a fresh orange, or                    *\n" +
            "* -> 2 representing a rotten orange.                      *\n" +
            "* Every minute, any fresh orange that is 4-directionally  *\n" +
            "* adjacent to a rotten orange becomes rotten.             *\n" +
            "*                                                         *\n" +
            "* Return the minimum number of minutes that must elapse   *\n" +
            "* until no cell has a fresh orange. If this is impossible,*\n" +
            "* return -1.                                              *\n" +
            "*                                                         *\n" +
            "* Example 1:                                              *\n" +
            "*    | Input: grid = [[2,1,1],[1,1,0],[0,1,1]]            *\n" +
            "*    | Output: 4                                          *\n" +
            "*                                                         *\n" +
            "* Example 2:                                              *\n" +
            "*    | Input: grid = [[2,1,1],[0,1,1],[1,0,1]]            *\n" +
            "*    | Output: -1                                         *\n" +
            "*    | Explanation: The orange in the bottom left corner  *\n" +
            "*             (row 2, column 0) is never rotten, because  *\n" +
            "*             rotting only happens 4-directionally.       *\n" +
            "*                                                         *\n" +
            "* Example 3:                                              *\n" +
            "*    | Input: grid = [[0,2]]                              *\n" +
            "*    | Output: 0                                          *\n" +
            "*    | Explanation: Since there are already no fresh      *\n" +
            "*             oranges at minute 0, the answer is just 0.  *\n" +
            "*                                                         *\n" +
            "* Constraints:                                            *\n" +
            "*    | m == grid.length                                   *\n" +
            "*    | n == grid[i].length                                *\n" +
            "*    | 1 <= m, n <= 10                                    *\n" +
            "*    | grid[i][j] is 0, 1, or 2.                          *\n" +
            "***********************************************************\n"
        println(codeChallenge)
    }

    fun orangesRotting(grid: Array<IntArray>): Int {
        printSolutionStart(grid)

        var freshOranges = 0
        var rottingMinutes = 0
        val rottingOranges = LinkedList<GridElement>()
        // Traverse through the matrix
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                val orange = GridElement.Orange(row, col)
                if (orange.isFresh(grid)) {
                    // Edge case, if a FreshOrange is only surrounded by spaces, we can quit and return -1
                    if (orange.isSurroundedBySpaces(grid)) {
                        return (-1).printSolutionEnd()
                    }
                    // Update the FreshOrange counter
                    freshOranges += 1
                } else if (orange.isRotten(grid)) {
                    // Add the rotten oranges found in the minute 0
                    rottingOranges.add(orange)
                }
            }
        }

        // In case that there are rotten oranges, insert a WaveInterval, used to mark each minute
        if (rottingOranges.isNotEmpty()) {
            rottingOranges.add(GridElement.WaveInterval)
        }

        // Traverse through Rotten Oranges queue
        while (rottingOranges.isNotEmpty()) {
            when (val element = rottingOranges.removeFirst()) {
                GridElement.WaveInterval -> {
                    // If it is a WaveInterval and there are still rotten oranges to process, increase the minute counter by 1 and
                    // add another WaveInterval separator
                    if (rottingOranges.isNotEmpty()) {
                        rottingOranges.add(GridElement.WaveInterval)
                        rottingMinutes += 1
                    }
                }
                is GridElement.Orange -> {
                    // If it is a RottenOrange, try to rotting nearby elements, and if succeeded, include the newly rotten oranges
                    // into the queue.
                    // Also, decrease the number of FreshOranges
                    val nextRottingRound = element.rottingNearby(grid)
                    nextRottingRound.forEach {
                        rottingOranges.add(it)
                        freshOranges -= 1
                    }
                }
            }
        }

        // If no more FreshOranges left, return the time took to rotten them all, otherwise return -1
        return if (freshOranges == 0) {
            rottingMinutes.printSolutionEnd()
        } else {
            (-1).printSolutionEnd()
        }
    }
}

sealed class GridElement {
    class Orange(private val row: Int, private val col: Int) : GridElement() {
        fun isFresh(grid: Array<IntArray>) : Boolean {
            return grid[row][col] == 1
        }
        fun isRotten(grid: Array<IntArray>) : Boolean {
            return grid[row][col] == 2
        }

        private fun isNotSpace(grid: Array<IntArray>) : Boolean {
            return grid[row][col] != 0
        }
        private fun rottenIt(grid: Array<IntArray>) {
            grid[row][col] = 2
        }

        fun isSurroundedBySpaces(grid: Array<IntArray>): Boolean {
            moveToDirections(grid).forEach { orange ->
                if (orange.isNotSpace(grid)) {
                    return false
                }
            }
            return true
        }
        fun rottingNearby(grid: Array<IntArray>): List<Orange> {
            val nextRottingRound = mutableListOf<Orange>()
            moveToDirections(grid).forEach { orange ->
                if (orange.isFresh(grid)) {
                    orange.rottenIt(grid)
                    nextRottingRound.add(orange)
                }
            }

            return nextRottingRound
        }

        private fun moveToDirections(grid: Array<IntArray>): List<Orange> {
            val orangeNeighbors = mutableListOf<Orange>()
            val movement = listOf(
                Orange(-1,0),
                Orange(0,1),
                Orange(1,0),
                Orange(0,-1))

            movement.forEach { direction ->
                val newRow = row + direction.row
                val newCol = col + direction.col

                if (newRow in grid.indices && newCol in grid[0].indices) {
                    orangeNeighbors.add(Orange(newRow, newCol))
                }
            }
            return orangeNeighbors
        }
    }
    object WaveInterval : GridElement()
}

private fun printSolutionStart(grid: Array<IntArray>) {
    println("===========================================================")
    println("- Input: grid = ${grid.printableArrayOfIntArray()}")
}

private fun Int.printSolutionEnd(): Int {
    println("- Output: $this")
    println("===========================================================\n")
    return this
}