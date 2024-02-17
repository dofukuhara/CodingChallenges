package numberofislands

import utils.printableArrayOfCharArray
import java.util.*

class NumberOfIslands {
    init {
        val codeChallenge = "\n" +
            "******************** Number of Islands ********************\n" +
            "*                                                         *\n" +
            "* Given an m x n 2D binary grid grid which represents a   *\n" +
            "* map of '1's (land) and '0's (water), return the number  *\n" +
            "* of islands.                                             *\n" +
            "*                                                         *\n" +
            "* An island is surrounded by water and is formed by       *\n" +
            "* connecting adjacent lands horizontally or vertically.   *\n" +
            "* You may assume all four edges of the grid are all       *\n" +
            "* surrounded by water.                                    *\n" +
            "*                                                         *\n" +
            "* Example 1:                                              *\n" +
            "*    | Input: grid = [                                    *\n" +
            "*        ['1','1','1','1','0'],                           *\n" +
            "*        ['1','1','0','1','0'],                           *\n" +
            "*        ['1','1','0','0','0'],                           *\n" +
            "*        ['0','0','0','0','0']                            *\n" +
            "*      ]                                                  *\n" +
            "*    | Output: 1                                          *\n" +
            "*                                                         *\n" +
            "* Example 2:                                              *\n" +
            "*    | Input: grid = [                                    *\n" +
            "*        ['1','1','0','0','0'],                           *\n" +
            "*        ['1','1','0','0','0'],                           *\n" +
            "*        ['0','0','1','0','0'],                           *\n" +
            "*        ['0','0','0','1','1']                            *\n" +
            "*      ]                                                  *\n" +
            "*    | Output: 3                                          *\n" +
            "*                                                         *\n" +
            "* Constraints:                                            *\n" +
            "*    | m == grid.length                                   *\n" +
            "*    | n == grid[i].length                                *\n" +
            "*    | 1 <= m, n <= 300                                   *\n" +
            "*    | grid[i][j] is '0' or '1'.                          *\n" +
            "***********************************************************\n"
        println(codeChallenge)
    }

    fun numIslands(grid: Array<CharArray>): Int {
        printSolutionStart(grid)

        // List of unvisited land points in the map
        val unvisitedPoints = mutableListOf<IslandCoord>()
        // Island counter
        var islandCounter = 0

        // Initialize 'unvisitedPoints' will all land points from the map
        for (row in grid.indices) {
            for (column in grid[0].indices) {
                if (grid[row][column] == '1') {
                    unvisitedPoints.add(IslandCoord(row, column))
                }
            }
        }

        // Traverse through all land points;
        while (unvisitedPoints.isNotEmpty()) {
            // Get the first land piece to analyze;
            val currentCoord = unvisitedPoints.removeFirst()
            // Then consume all of its land neighbours (if any, by removing them from the 'unvisitedPoints');
            consumeSurroundingLand(grid, unvisitedPoints, currentCoord)
            // And update the Island Counter.
            islandCounter += 1
        }

        return islandCounter.printSolutionEnd()
    }

    private fun consumeSurroundingLand(map: Array<CharArray>, unvisitedPoints: MutableList<IslandCoord>, coord: IslandCoord) {
        // Queue to hold all island land neighbours, to be analyzed in a wave flow
        val landQueue = LinkedList<IslandCoord>().apply { add(coord) }

        // Traverse through the queue
        while (landQueue.isNotEmpty()) {
            val currentCoord = landQueue.removeFirst()
            // Try to move to all possible neighbour directions (North, West, East, South)
            Movement.values().forEach { direction ->
                val newCoord = currentCoord.moveTo(map, direction)
                // If the movements result in a valid position in the map
                if (newCoord != null) {
                    // If this movement results in a point that was not consumed before, consume it and update the queue,
                    // so that we can analyze the neighbour's neighbour
                    if (unvisitedPoints.contains(newCoord)) {
                        unvisitedPoints.remove(newCoord)
                        landQueue.add(newCoord)
                    }
                }
            }
        }
    }
}

// Data class that represents an Island Point in the map
data class IslandCoord(val row: Int, val col: Int) {
    // Method that will try to navigate, from the given point following a direction, return a valid point in the map or null
    fun moveTo(map: Array<CharArray>, movement: Movement): IslandCoord? {
        val newRow = row + movement.row
        val newCol = col + movement.col

        return if (newRow in map.indices && newCol in map[0].indices) {
            IslandCoord(newRow, newCol)
        } else {
            null
        }
    }
}

// Enum class for the possible movements in the map (North, West, East, South), and how it is represented in the matrix
enum class Movement(val row: Int, val col: Int) {
    N(-1, 0), W(0, -1), E(0, 1), S(1, 0)
}

private fun printSolutionStart(map: Array<CharArray>) {
    println("===========================================================")
    println("- Input: grid = ${map.printableArrayOfCharArray()}")

}

private fun Int.printSolutionEnd(): Int {
    println("- Output: $this")
    println("===========================================================\n")
    return this
}