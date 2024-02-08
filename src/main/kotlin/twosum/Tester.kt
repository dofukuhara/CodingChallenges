package twosum

import utils.assertWith

fun main() {
    val solution = TwoSum()

    solution.twoSum(intArrayOf(2,7,11,15), 9).assertWith("[0,1]")
    solution.twoSum(intArrayOf(3,2,4), 6).assertWith("[1,2]")
    solution.twoSum(intArrayOf(3,3), 6).assertWith("[0,1]")
    solution.twoSum(intArrayOf(3,2), 6).assertWith("[]")

}
