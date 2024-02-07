package ransomnote

class RansomNote {

    init {
        val codeChallenge =
            "\n"+
            "************************* Ransom Note *************************\n" +
            "* Given two strings ransomNote and magazine, return true if   *\n" +
            "* ransomNote can be constructed by using the letters from     *\n" +
            "* magazine and false otherwise.                               *\n" +
            "* Each letter in magazine can only be used once in ransomNote.*\n" +
            "*                                                             *\n" +
            "* Example 1:                                                  *\n" +
            "*    | Input: ransomNote = \"a\", magazine = \"b\"                *\n" +
            "*    | Output: false                                          *\n" +
            "*                                                             *\n" +
            "* Example 2                                                   *\n" +
            "*    | Input: ransomNote = \"aa\", magazine = \"ab\"              *\n" +
            "*    | Output: false                                          *\n" +
            "*                                                             *\n" +
            "* Example 3                                                   *\n" +
            "*    | Input: ransomNote = \"aa\", magazine = \"aab\"             *\n" +
            "*    | Output: true                                           *\n" +
            "*                                                             *\n" +
            "* Constraints:                                                *\n" +
            "*    | 1 <= ransomNote.length, magazine.length <= 10^5        *\n" +
            "*    | ransomNote and magazine consist of lowercase English   *\n" +
            "*      letters.                                               *\n" +
            "***************************************************************\n"
        println(codeChallenge)
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {

        println("===============================================================")
        println("- Input: ransomNote = \"$ransomNote\", magazine = \"$magazine\"")

        val magazineCharHashMap = buildMagazineCharHashMap(magazine)

        ransomNote.forEach { charForRandomNote ->
            if (!magazineCharHashMap.contains(charForRandomNote)) {
                // Char required for Ransom Note didn't find in the Magazine
                return false.printEndOfSolution()
            } else if (magazineCharHashMap[charForRandomNote] == 0) {
                // Magazine does not have enough letter for this char
                return false.printEndOfSolution()
            } else {
                val charCount = magazineCharHashMap[charForRandomNote]
                charCount?.let {
                    magazineCharHashMap[charForRandomNote] = it - 1
                }
            }
        }

        return true.printEndOfSolution()
    }

    private fun buildMagazineCharHashMap(magazine: String): MutableMap<Char, Int> {
        val hashMap = mutableMapOf<Char, Int>()
        magazine.forEach { char ->
            hashMap[char] = hashMap[char]?.let { charCount -> charCount + 1 } ?: 1
        }
        return hashMap
    }
}

private fun Boolean.printEndOfSolution(): Boolean {
    println("- Output: $this")
    println("===============================================================\n")

    return this
}