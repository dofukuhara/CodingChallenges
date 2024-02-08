package ransomnote

import utils.assertWith

fun main() {
    val solution = RansomNote()

    solution.canConstruct(ransomNote = "a", magazine = "b").assertWith(false)
    solution.canConstruct(ransomNote = "aa", magazine = "ab").assertWith(false)
    solution.canConstruct(ransomNote = "aa", magazine = "aab").assertWith(true)

}