package maximumsubarray

import utils.assertWith

fun main() {
    val solution = MaximumSubarray()

    solution.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)).assertWith(6)
    solution.maxSubArray(intArrayOf(1)).assertWith(1)
    solution.maxSubArray(intArrayOf(5,4,-1,7,8)).assertWith(23)
    solution.maxSubArray(intArrayOf(-5,-4,-1,-7,-8)).assertWith(-1)
}
