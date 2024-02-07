package ransomnote

fun main() {
    val solution = RansomNote()

    solution.canConstruct(ransomNote = "a", magazine = "b")
    solution.canConstruct(ransomNote = "aa", magazine = "ab")
    solution.canConstruct(ransomNote = "aa", magazine = "aab")

}