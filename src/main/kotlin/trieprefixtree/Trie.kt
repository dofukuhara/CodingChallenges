package trieprefixtree

class Trie {

    init {
        val codeChallenge = "\n" +
            "************************ Trie ************************\n" +
            "* A trie (pronounced as \"try\") or prefix tree is a   *\n" +
            "* tree data structure used to efficiently store and  *\n" +
            "* retrieve keys in a dataset of strings.             *\n" +
            "* There are various applications of this data        *\n" +
            "* structure, such as autocomplete and spellchecker.  *\n" +
            "*                                                    *\n" +
            "* Implement the Trie class:                          *\n" +
            "*                                                    *\n" +
            "* Trie() Initializes the trie object.                *\n" +
            "*   - void insert(String word) Inserts the string    *\n" +
            "*     word into the trie.                            *\n" +
            "*   - boolean search(String word) Returns true if    *\n" +
            "*     the string word is in the trie (i.e., was      *\n" +
            "*     inserted before), and false otherwise.         *\n" +
            "*   - boolean startsWith(String prefix) Returns true *\n" +
            "*     if there is a previously inserted string word  *\n" +
            "*     that has the prefix prefix, and false          *\n" +
            "*     otherwise.                                     *\n" +
            "*                                                    *\n" +
            "* Example and explanation:                           *\n" +
            "*    | Trie trie = new Trie();                       *\n" +
            "*    | trie.insert(\"apple\");                         *\n" +
            "*    | trie.search(\"apple\");   // return True        *\n" +
            "*    | trie.search(\"app\");     // return False       *\n" +
            "*    | trie.startsWith(\"app\"); // return True        *\n" +
            "*    | trie.insert(\"app\");                           *\n" +
            "*    | trie.search(\"app\");     // return True        *\n" +
            "*                                                    *\n" +
            "* Constraints:                                       *\n" +
            "*    | 1 <= word.length, prefix.length <= 2000       *\n" +
            "*    | word and prefix consist only of lowercase     *\n" +
            "*      English letters.                              *\n" +
            "*    | At most 3 * 10^4 calls in total will be made  *\n" +
            "*      to insert, search, and startsWith.            *\n" +
            "******************************************************\n"
        println(codeChallenge)
    }

    // The root node of the Trie.
    private val trieRoot = Node(Char.MIN_VALUE)

    fun insert(word: String) {
        // Initialize the Trie runner with its root.
        var trieRunner = trieRoot
        word.forEach { char ->
            // If the current char is not present in the trie children, create it.
            // Then update the runner with node and proceed with the loop.
            trieRunner = trieRunner.children.computeIfAbsent(char) { Node(value = char) }
        }

        // Add a terminator char, it will be used by search(word: String)
        trieRunner.addTerminalLetter()
    }

    fun search(word: String): Boolean {
        return lookupWord(word, true)
    }

    fun startsWith(prefix: String): Boolean {
        return lookupWord(prefix, false)
    }

    // As 'search' and 'startsWith' are very similar, we will wrap the logic into this 'lookupWord' and
    // use the flag 'fullMatch' to decide if the whole word is found or just check if it starts with this prefix.
    private fun lookupWord(word: String, fullMatch: Boolean): Boolean {
        var trieRunner: Node? = trieRoot
        word.forEach { char ->
            trieRunner = trieRunner?.children?.get(char)

            if (trieRunner == null) return false
        }

        return if (fullMatch) {
            trieRunner.isTerminalLetter()
        } else {
            true
        }
    }
}

data class Node(
    val value: Char,
    var children: MutableMap<Char, Node> = mutableMapOf()
)

private fun Node.addTerminalLetter() {
    this.children[Char.MIN_VALUE] = Node(Char.MIN_VALUE)
}

private fun Node?.isTerminalLetter(): Boolean {
    return this != null && this.children.isEmpty() || this?.children?.contains(Char.MIN_VALUE) == true
}