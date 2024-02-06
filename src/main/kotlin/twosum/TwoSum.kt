package twosum

class TwoSum {

    init {
        val codingChallenge =
            "\n" +
            "************************ Two Sum ************************\n" +
            "* Given an array of integers nums and an integer target,*\n" +
            "* return indices of the two numbers such that they add  *\n" +
            "* up to target.                                         *\n" +
            "* You may assume that each input would have exactly one *\n" +
            "* solution, and you may not use the same element twice. *\n" +
            "* You can return the answer in any order.               *\n" +
            "*                                                       *\n" +
            "* Example 1:                                            *\n" +
            "*    | Input: nums = [2,7,11,15], target = 9            *\n" +
            "*    | Output: [0,1]                                    *\n" +
            "*    | Explanation:                                     *\n" +
            "*      Because nums[0] + nums[1] == 9, we return [0, 1].*\n" +
            "* Example 2:                                            *\n" +
            "*    | Input: nums = [3,2,4], target = 6                *\n" +
            "*    | Output: [1,2]                                    *\n" +
            "* Example 3:                                            *\n" +
            "*    | Input: nums = [3,3], target = 6                  *\n" +
            "*    | Output: [0,1]                                    *\n"+
            "*********************************************************\n"

        println(codingChallenge)
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {

        println("=========================================================")
        println("- Input: nums = ${nums.getPrintableString()}, target = $target")

        val hashMap = generateHashOfElementAndIndexes(nums)

        hashMap.forEach { element ->
            val firstElement = element.key
            val neededElement = target - firstElement

            if (hashMap.containsKey(neededElement)) {
                /*
                    Two cases to handle:
                    1) If `neededElement` == `firstElement` then indexes must be different
                        -> In other words, element.value.size > 1
                    2) If `neededElement` != `firstElement` then solution was found
                */
                if (neededElement == firstElement) {
                    if (element.value.size > 1) {
                        return intArrayOf(
                            element.value[0], element.value[1]
                        ).printEndOfSolution()
                    }
                } else {
                    return intArrayOf(
                        element.value.first(),
                        hashMap[neededElement]?.first()!!
                    ).printEndOfSolution()
                }
            }
        }

        return intArrayOf().printEndOfSolution()
    }

    /*
        Given an array of Integers, return a map of key (element value) and value (indexes of this element in the array).
        Example:
            IntArray: [ 10, 20, 30, 40, 20 ]
            Map: { 10=[0], 20=[1, 4], 30=[2], 40=[3] }
     */
    private fun generateHashOfElementAndIndexes(nums: IntArray): Map<Int,List<Int>> {
        val hashMap: MutableMap<Int,List<Int>> = mutableMapOf()

        nums.forEachIndexed { index, element ->
            val listOfIndex = hashMap[element]?.plus(index) ?: listOf(index)
            hashMap[element] = listOfIndex
        }

        return hashMap
    }
}

private fun IntArray.getPrintableString(): String {
    val messageToPrint = StringBuilder("[")
    this.forEachIndexed { index, element ->
        messageToPrint.append(element)
        if (index < this.size - 1) {
            messageToPrint.append(",")
        }
    }
    return messageToPrint.append("]").toString()
}

private fun IntArray.printEndOfSolution(): IntArray {
    println("- Output: ${this.getPrintableString()}")
    println("=========================================================")
    println()
    return this
}