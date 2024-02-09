package kclosestpointstoorigin

import utils.assertWith

fun main() {

    val solution = KClosestPointsToOrigin()

    solution.kClosest(
        points = arrayOf(
            intArrayOf(1,3),
            intArrayOf(-2,2)),
        k = 1
    ).assertWith("[[-2,2]]")

    solution.kClosest(
        points = arrayOf(
            intArrayOf(3,3),
            intArrayOf(5,-1),
            intArrayOf(-2,4)),
        k = 2
    ).assertWith("[[3,3],[-2,4]]")
}