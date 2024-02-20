package regesmatch

import utils.assertWith

fun main() {
    val solution = RegexMatch()

    solution.isMatch(string = "", pattern = "").assertWith(true)
    solution.isMatch(string = "a", pattern = "").assertWith(false)
    solution.isMatch(string = "", pattern = "a").assertWith(false)
    solution.isMatch(string = "aa", pattern = "a").assertWith(false)
    solution.isMatch(string = "aa", pattern = "a*").assertWith(true)
    solution.isMatch(string = "ab", pattern = ".*").assertWith(true)
    solution.isMatch(string = "aab", pattern = "c*a*b").assertWith(true)
    solution.isMatch(string = "mississippi", pattern = "mis*is*ip*.").assertWith(true)
    solution.isMatch(string = "aaa", pattern = "a*a").assertWith(true)
}