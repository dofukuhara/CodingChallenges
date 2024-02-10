package threesum

import utils.assertWith

fun main() {
    val solution = ThreeSum()

    solution.threeSum(intArrayOf(-1,0,1,2,-1,-4)).assertWith("[[-1, 0, 1], [-1, -1, 2]]")
    solution.threeSum(intArrayOf(0,1,1)).assertWith("[]")
    solution.threeSum(intArrayOf(0,0,0)).assertWith("[[0, 0, 0]]")
    solution.threeSum(intArrayOf(3,0,-2,-1,1,2)).assertWith("[[-2, 0, 2], [-1, 0, 1], [-2, -1, 3]]")
}