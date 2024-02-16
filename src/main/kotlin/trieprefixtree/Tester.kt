package trieprefixtree

import utils.assertWith

fun main() {
    val solution = Trie()

    println("======================================================")
    println("Initializing test case")

    solution.insert("apple")
    solution.search("apple").assertWith(true)
    solution.search("app").assertWith(false)
    solution.startsWith("app").assertWith(true)
    solution.insert("app")
    solution.search("app").assertWith(true)

    println("Finished test case")
    println("======================================================")
}