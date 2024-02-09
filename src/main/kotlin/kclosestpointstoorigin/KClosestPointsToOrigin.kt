package kclosestpointstoorigin

import utils.printableArrayOfIntArray
import kotlin.math.sqrt

class KClosestPointsToOrigin {
    init {
        val codeChallenge = "\n" +
            "************ K Closest Points to Origin *************\n" +
            "* Given an array of points where points[i] =        *\n" +
            "* [xi, yi] represents a point on the X-Y plane and  *\n" +
            "* an integer k, return the k closest points to the  *\n" +
            "* origin (0, 0).                                    *\n" +
            "*                                                   *\n" +
            "* The distance between two points on the X-Y plane  *\n" +
            "* is the Euclidean distance (i.e., √(x1 - x2)2 +    *\n" +
            "* (y1 - y2)2).                                      *\n" +
            "*                                                   *\n" +
            "* You may return the answer in any order. The       *\n" +
            "* answer is guaranteed to be unique (except for the *\n" +
            "* order that it is in).                             *\n" +
            "*                                                   *\n" +
            "* Example 1:                                        *\n" +
            "*    | Input: points = [[1,3],[-2,2]], k = 1        *\n" +
            "*    | Output: [[-2,2]]                             *\n" +
            "*    | Explanation:                                 *\n" +
            "*      The distance between (1, 3) and the origin   *\n" +
            "*        is sqrt(10).                               *\n" +
            "*      The distance between (-2,2) and the origin   *\n" +
            "*        is sqrt(8).                                *\n" +
            "*      Since sqrt(8) < sqrt(10), (-2, 2) is closer  *\n" +
            "*        to the origin.                             *\n" +
            "*      We only want the closest k = 1 points from   *\n" +
            "*        the origin, so the answer is just [[-2,2]].*\n" +
            "*                                                   *\n" +
            "* Example 2:                                        *\n" +
            "*    | Input: points = [[3,3],[5,-1],[-2,4]], k = 2 *\n" +
            "*    | Output: [[3,3],[-2,4]]                       *\n" +
            "*    | Explanation:                                 *\n" +
            "*      The answer [[-2,4],[3,3]] would also be      *\n" +
            "*        accepted.                                  *\n" +
            "*                                                   *\n" +
            "* Constraints:                                      *\n" +
            "*    | 1 <= k <= points.length <= 10^4              *\n" +
            "*    | -10^4 <= xi, yi <= 10^4                      *\n" +
            "*****************************************************\n"

        println(codeChallenge)
    }
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        printSolutionStart(points, k)

        return if (points.isEmpty()) {
            points.printSolutionEnd()
        } else {
            points.sortedBy {
                val x = it[0]
                val y = it[1]

                sqrt((x * x + y * y.toDouble()))
            }.take(k).toTypedArray().printSolutionEnd()
        }
    }

    private fun printSolutionStart(points: Array<IntArray>, k: Int) {
        println("=====================================================")
        println("- Input: points = ${points.printableArrayOfIntArray()}, k = $k")
    }

    private fun Array<IntArray>.printSolutionEnd(): Array<IntArray> {
        println("- Output: ${this.printableArrayOfIntArray()}")
        println("=====================================================\n")
        return this
    }
}