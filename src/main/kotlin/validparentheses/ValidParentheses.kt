package validparentheses

import validparentheses.data.Parentheses
import validparentheses.data.getParenthesesType
import java.util.Stack

class ValidParentheses {

    init {
        val codingChallenge =
            "\n" +
                    "************************ Valid Parentheses ************************\n" +
                    "* Given a string s containing just the characters '(', ')', '{',  *\n" +
                    "* '}', '[' and ']', determine if the input string is valid.       *\n" +
                    "* An input string is valid if:                                    *\n" +
                    "*   1. Open brackets must be closed by the same type of brackets. *\n" +
                    "*   2. Open brackets must be closed in the correct order.         *\n" +
                    "*   3. Every close bracket has a corresponding open bracket of    *\n" +
                    "*      same type.                                                 *\n" +
                    "*                                                                 *\n" +
                    "* Example 1:                                                      *\n" +
                    "*    | Input: s = \"()\"                                            *\n" +
                    "*    | Output: true                                               *\n" +
                    "* Example 2:                                                      *\n" +
                    "*    | Input: s = \"()[]{}\"                                        *\n" +
                    "*    | Output: true                                               *\n" +
                    "* Example 3:                                                      *\n" +
                    "*    | Input: s = \"(]\"                                            *\n" +
                    "*    | Output: false                                              *\n" +
                    "*******************************************************************\n"

        println(codingChallenge)
    }

    fun isValid(s: String): Boolean {

        println("===================================================================")
        println("- Input: s = \"$s\"")

        // Edge case - the testing string is empty, or it has to have an even number of chars
        if (s.isEmpty() || s.length % 2 != 0) {
            return false.printEndOfSolution()
        }

        val stack = Stack<Parentheses>()

        s.forEach { char ->
            val parenthesesType = char.getParenthesesType()
            // Edge case - a non-parentheses char found in the string.
            if (parenthesesType is Parentheses.Unknown) {
                return false.printEndOfSolution()
            }

            if (parenthesesType.isOpen) {
                // Just add it in the stack
                stack.push(parenthesesType)
            } else {
                // Check if the top element on the stack is an open match for this closing parentheses
                val target = stack.pop()
                if (parenthesesType.isAnClosingMatchOf(target).not()) {
                    return false.printEndOfSolution()
                }
            }
        }

        return stack.isEmpty().printEndOfSolution()
    }
}

private fun Boolean.printEndOfSolution(): Boolean {
    println("- Output: $this")
    println("===================================================================\n")

    return this
}