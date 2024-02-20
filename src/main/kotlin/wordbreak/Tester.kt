package wordbreak

import utils.assertWith

fun main() {
    val solution = WordBreak()

    solution.wordBreak("leetcode", listOf("leet","code")).assertWith(true)
    solution.wordBreak("catsandog", listOf("cats","dog","sand","and","cat")).assertWith(false)
    solution.wordBreak("aaaaaaa", listOf("aaaa","aa")).assertWith(false)
}