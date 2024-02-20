package wordbreak

class WordBreak {
    init {
        val codeChallenge = "\n" +
            "*********************************** Word Break ***********************************\n" +
            "*                                                                                *\n" +
            "* Given a string s and a dictionary of strings wordDict, return true if s can be *\n" +
            "* segmented into a space-separated sequence of one or more dictionary words.     *\n" +
            "*                                                                                *\n" +
            "* Note that the same word in the dictionary may be reused multiple times in the  *\n" +
            "* segmentation.                                                                  *\n" +
            "*                                                                                *\n" +
            "* Example 1:                                                                     *\n" +
            "*    | nput: s = 'leetcode', wordDict = ['leet','code']                          *\n" +
            "*    | Output: true                                                              *\n" +
            "*    | Explanation: Return true because 'leetcode' can be segmented as           *\n" +
            "*                   'leet code'.                                                 *\n" +
            "*                                                                                *\n" +
            "* Example 2:                                                                     *\n" +
            "*    | Input: s = 'applepenapple', wordDict = ['apple','pen']                    *\n" +
            "*    | Output: true                                                              *\n" +
            "*    | Explanation: Return true because 'applepenapple' can be segmented as      *\n" +
            "*                   'apple pen apple'.                                           *\n" +
            "*                   Note that you are allowed to reuse a dictionary word.        *\n" +
            "*                                                                                *\n" +
            "* Example 3:                                                                     *\n" +
            "*    | Input: s = 'catsandog', wordDict = ['cats','dog','sand','and','cat']      *\n" +
            "*    | Output: false                                                             *\n" +
            "*                                                                                *\n" +
            "* Constraints:                                                                   *\n" +
            "*    | 1 <= s.length <= 300                                                      *\n" +
            "*    | 1 <= wordDict.length <= 1000                                              *\n" +
            "*    | 1 <= wordDict[i].length <= 20                                             *\n" +
            "*    | s and wordDict[i] consist of only lowercase English letters.              *\n" +
            "*    | All the strings of wordDict are unique.                                   *\n" +
            "**********************************************************************************\n"

        println(codeChallenge)
    }

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        printSolutionStart(s, wordDict)

        // Initialize the Trie structure, and set the root node with 'isFinalWord = true'
        val wordDictTrie = Trie().apply { isFinalWord = true }
        // Build up the Word Dictionary Trie with the words from wordDict list
        wordDict.forEach { word ->
            wordDictTrie.insert(word)
        }

        // Initialize all possible states with the word dictionary tree built above
        var allPossibleStates = mutableListOf(wordDictTrie)
        s.forEach { char ->
            val statesFilter = mutableListOf<Trie>()
            var reIncludeDict = false
            for (possibleState in allPossibleStates) {
                // Check if the 'char' being evaluated can be found at any root of the possible states
                val trieNode = possibleState.next[char] ?: continue

                // In case that this letter represents a final word in the dictionary, we can set to re-include the
                // whole dictionary, as will need to check the next char against the initial letter of the dictionary
                if (trieNode.isFinalWord) {
                    reIncludeDict = true
                }

                // Despite it being a terminal word or not, it can have more words after this, example:
                // char evaluated: 't' -> words: cat, cats, catering
                if (trieNode.next.isNotEmpty()) {
                    statesFilter.add(trieNode)
                }
            }

            if (reIncludeDict) {
                statesFilter.add(wordDictTrie)
            }

            allPossibleStates = statesFilter

            // If after applying the filter on all possible states and no more states are left, we can early return from here.
            if (allPossibleStates.isEmpty()) {
                return false.printSolutionEnd()
            }
        }

        // If 'isFinalWord == true', it means that initial 'wordDictTrie' was re-included in the possible states,
        // meaning that the target word was fully evaluated against the dictionary.
        return allPossibleStates.any { it.isFinalWord }.printSolutionEnd()
    }
}

// Structure that represents a Trie node
class Trie {
    var isFinalWord: Boolean = false
    val next = mutableMapOf<Char,Trie>()

    fun insert(word: String) {
        var runner = this
        word.forEach { char ->
            runner = runner.next.getOrPut(char) { Trie() }
        }
        // After setting the last char of the word, mark it as a completed word
        runner.isFinalWord = true
    }
}

private fun printSolutionStart(s: String, wordDict: List<String>) {
    println("==================================================================================")
    println("- Input: s = \"$s\", wordDict = $wordDict ")
}

private fun Boolean.printSolutionEnd(): Boolean {
    println("- Output: $this")
    println("==================================================================================\n")
    return this
}