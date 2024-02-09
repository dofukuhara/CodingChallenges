package longestuniquesubstring

import java.util.*

class LongestUniqueSubstring {
    init {
        val codeChallenge = "\n" +
            "***** Longest Substring Without Repeating Characters *****\n" +
            "* Given a string s, find the length of the longest       *\n" +
            "* substring without repeating characters.                *\n" +
            "*                                                        *\n" +
            "*                                                        *\n" +
            "* Example 1:                                             *\n" +
            "*    | Input: s = \"abcabcbb\"                             *\n" +
            "*    | Output: 3                                         *\n" +
            "*    | Explanation: The answer is \"abc\", with the length *\n" +
            "*                   of 3.                                *\n" +
            "*                                                        *\n" +
            "* Example 2:                                             *\n" +
            "*    | Input: s = \"bbbbb\"                                *\n" +
            "*    | Output: 1                                         *\n" +
            "*    | Explanation: The answer is \"b\", with the length   *\n" +
            "*                   of 1.                                *\n" +
            "*                                                        *\n" +
            "* Example 3:                                             *\n" +
            "*    | Input: s = \"pwwkew\"                               *\n" +
            "*    | Output: 3                                         *\n" +
            "*    | Explanation: The answer is \"wke\", with the length *\n" +
            "*                   of 3.                                *\n" +
            "*      Notice that the answer must be a substring, \"pwke\"*\n" +
            "*      is a subsequence and not a substring.             *\n" +
            "*                                                        *\n" +
            "* Constraints:                                           *\n" +
            "*    | 0 <= s.length <= 5 * 10^4                         *\n" +
            "*    | s consists of English letters, digits, symbols    *\n" +
            "*      and spaces.                                       *\n" +
            "**********************************************************\n"

        println(codeChallenge)
    }
    fun lengthOfLongestSubstring(s: String): Int {
        printSolutionStart(s)

        // Edge case: input size of 0 or 1, return its size
        if (s.length <= 1) return s.length.printSolutionEnd()

        // Queue to hold the 'current' unique string
        val substringQueue = LinkedList<Char>()
        // Property to hold the 'current' unique string size
        var maxLengthSoFar = 0
        // Property to hold the max unique string size over all unique strings
        var maxLengthOverall = 0

        s.forEach { char ->
            if (!substringQueue.contains(char)) {
                /*
                    If current char is not in the queue, just include it in the queue and update the value
                    of the maxLengthSoFar and maxLengthOverall counters
                 */
                substringQueue.add(char)
                maxLengthSoFar += 1
                maxLengthOverall = maxOf(maxLengthSoFar, maxLengthOverall)
            } else {
                // If the current char was found in the queue...
                // Add the current char (at the end of the queue)
                substringQueue.add(char)
                // Keep removing all the chars from the head of the queue, until we find the duplicated char
                var headFromSubstringQueue = substringQueue.removeFirst()
                while (headFromSubstringQueue != char) {
                    headFromSubstringQueue = substringQueue.removeFirst()
                }
                // Update the maxLengthSoFar with the current unique string size
                maxLengthSoFar = substringQueue.size
                // No need to update maxLengthOverall, because as we are removing chars from the queue, there is no
                // way to this substring be greater than the previous one (it can be at most, the same size).
            }
        }

        return maxLengthOverall.printSolutionEnd()
    }

    private fun printSolutionStart(s: String) {
        println("==========================================================")
        println("- Input: s = $s")
    }

    private fun Int.printSolutionEnd(): Int {
        println("- Output: $this")
        println("==========================================================\n")
        return this

    }
}