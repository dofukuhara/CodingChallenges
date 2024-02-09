package longestuniquesubstring

import utils.assertWith

fun main() {
    val solution = LongestUniqueSubstring()

    solution.lengthOfLongestSubstring("abcabcbb").assertWith(3)
    solution.lengthOfLongestSubstring("bbbbb").assertWith(1)
    solution.lengthOfLongestSubstring("pwwkew").assertWith(3)
    solution.lengthOfLongestSubstring("dvdf").assertWith(3)
}