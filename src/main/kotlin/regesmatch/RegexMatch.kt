package regesmatch


class RegexMatch {
    init {
        val codeChallenge = "\n" +
                "*************************** Regular Expression Matching ***************************\n" +
                "*                                                                                 *\n" +
                "* Given an input string s and a pattern p, implement regular expression matching  *\n" +
                "* with support for '.' and '*' where:                                             *\n" +
                "*   | '.' Matches any single character.                                           *\n" +
                "*   | '*' Matches zero or more of the preceding element.                          *\n" +
                "*   | The matching should cover the entire input string (not partial).            *\n" +
                "*                                                                                 *\n" +
                "* Example 1:                                                                      *\n" +
                "*    | Input: s = \"aa\", p = \"a\"                                                   *\n" +
                "*    | Output: false                                                              *\n" +
                "*    | Explanation: \"a\" does not match the entire string \"aa\".                    *\n" +
                "*                                                                                 *\n" +
                "* Example 2:                                                                      *\n" +
                "*    | Input: s = \"aa\", p = \"a*\"                                                  *\n" +
                "*    | Output: true                                                               *\n" +
                "*    | Explanation: '*' means zero or more of the preceding element, 'a'.         *\n" +
                "*                    Therefore, by repeating 'a' once, it becomes \"aa\".           *\n" +
                "*                                                                                 *\n" +
                "* Example 3:                                                                      *\n" +
                "*    | Input: s = \"ab\", p = \".*\"                                                  *\n" +
                "*    | Output: true                                                               *\n" +
                "*    | Explanation: \".*\" means \"zero or more (*) of any character (.)\".           *\n" +
                "*                                                                                 *\n" +
                "* Constraints:                                                                    *\n" +
                "*    | 1 <= s.length <= 20                                                        *\n" +
                "*    | 1 <= p.length <= 20                                                        *\n" +
                "*    | s contains only lowercase English letters.                                 *\n" +
                "*    | p contains only lowercase English letters, '.', and '*'.                   *\n" +
                "*    | It is guaranteed for each appearance of the character '*', there will be a *\n" +
                "*      previous valid character to match.                                         *\n" +
                "***********************************************************************************\n"

        println(codeChallenge)
    }
    fun isMatch(string: String, pattern: String): Boolean {
        printSolutionStart(string, pattern)

        val result = isMatchRecursionHelper(string, pattern)

        return result.printSolutionEnd()
    }

    private fun isMatchRecursionHelper(string: String, pattern: String): Boolean {
        // Base/Edge cases
        if (pattern.isEmpty()) {
            return string.isEmpty()
        }

        val firstTest = string.isNotEmpty() && (pattern[0] == '.' || string[0] == pattern[0])

        return if (pattern.length >= 2 && pattern[1] == '*') {
            // If the next element of the pattern is a '*', it can have:
            // 1) zero items (that's why not appending 'firstTest' in the return) of the preceding char -or-
            // 2) 1 (that's why appending 'firstTest' in the return) or more items of the preceding char
            isMatchRecursionHelper(string, pattern.substring(2)) || firstTest && isMatchRecursionHelper(string.substring(1), pattern)
        } else {
            // If it is a single char test, append the result of current result + the next verification
            firstTest && isMatchRecursionHelper(string.substring(1), pattern.substring(1))
        }
    }
}

private fun printSolutionStart(string: String, pattern: String) {
    println("===================================================================================")
    println("- Input: s = \"$string\", p = \"$pattern\"")
}

private fun Boolean.printSolutionEnd(): Boolean {
    println("- Output: $this")
    println("===================================================================================\n")
    return this
}