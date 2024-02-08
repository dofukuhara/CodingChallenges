package maximumsubarray

fun main() {
    val solution = MaximumSubarray()

    solution.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4))
    solution.maxSubArray(intArrayOf(1))
    solution.maxSubArray(intArrayOf(5,4,-1,7,8))
    solution.maxSubArray(intArrayOf(-5,-4,-1,-7,-8))
}