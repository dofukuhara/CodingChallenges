package addbinary

import java.util.*

class AddBinary {
    init {
        val codeChallenge = "\n" +
            "********************* Add Binary **********************\n" +
            "* Given two binary strings a and b, return their sum  *\n" +
            "* as a binary string.                                 *\n" +
            "*                                                     *\n" +
            "* Example 1:                                          *\n" +
            "*    | Input: a = \"11\", b = \"1\"                       *\n" +
            "*    | Output: \"100\"                                  *\n" +
            "*                                                     *\n" +
            "* Example 2:                                          *\n" +
            "*    | Input: a = \"1010\", b = \"1011\"                  *\n" +
            "*    | Output: \"10101\"                                *\n" +
            "*                                                     *\n" +
            "* Constraints:                                        *\n" +
            "*    | 1 <= a.length, b.length <= 10^4                *\n" +
            "*    | a and b consist only of '0' or '1' characters. *\n" +
            "*    | Each string does not contain leading zeros     *\n" +
            "*      except for the zero itself.                    *\n" +
            "*******************************************************\n"

        println(codeChallenge)
    }

    fun addBinary(a: String, b: String): String {
        printSolutionStart(a, b)

        /*
            Using Stack as we are going to read the binary number from right to left
         */
        val stackOfA = Stack<Char>().apply {
            a.forEach { char ->
                push(char)
            }
        }
        val stackOfB = Stack<Char>().apply {
            b.forEach { char ->
                push(char)
            }
        }

        // Using Stack to hold the result as we will then read most significant bit first
        val stackOfResult = Stack<Char>()

        var carryOver = 0
        while (stackOfA.isNotEmpty() || stackOfB.isNotEmpty()) {
            val elementFromA = if (stackOfA.isNotEmpty()) {
                stackOfA.pop().charToInt()
            } else {
                0
            }
            val elementFromB = if (stackOfB.isNotEmpty()) {
                stackOfB.pop().charToInt()
            } else {
                0
            }

            when (elementFromA + elementFromB + carryOver) {
                /*
                    0 + 0 [no carry over from previous operation]
                    - Result = 0
                    - Carry Over = 0
                 */
                0 -> {
                    stackOfResult.push('0')
                    carryOver = 0
                }
                /*
                    1 + 0 (or 0 + 1 or 0 + 0 + carryOver[1])
                    - Result = 1
                    - Carry Over = 0
                 */
                1 -> {
                    stackOfResult.push('1')
                    carryOver = 0
                }
                /*
                    1 + 1 (or 0 + 1 + carryOver[1] or 1 + 0 + carryOver[1])
                    - Result = 0
                    - Carry Over = 1
                 */
                2 -> {
                    stackOfResult.push('0')
                    carryOver = 1
                }
                /*
                    1 + 1 + carryOver[1]
                    - Result = 1
                    - Carry Over = 1
                 */
                3 -> {
                    stackOfResult.push('1')
                    carryOver = 1
                }
            }
        }

        // If after finishing processing the sum there is still a pending carry over,
        // then add it at the start of the result
        if (carryOver == 1) {
            stackOfResult.push('1')
        }

        // Building the response as a string
        val resultAsString = StringBuilder()
        while(stackOfResult.isNotEmpty()) {
            resultAsString.append(stackOfResult.pop())
        }

        return resultAsString.toString().printSolutionEnd()
    }
}

private fun Char.charToInt(): Int {
    return if (this == '1') {
        1
    } else {
        0
    }
}

private fun printSolutionStart(a: String, b: String) {
    println("=======================================================")
    println("- Input: a = \"$a\", b = \"$b\"")
}

private fun String.printSolutionEnd(): String {
    println("- Output: \"$this\"")
    println("=======================================================\n")
    return this
}