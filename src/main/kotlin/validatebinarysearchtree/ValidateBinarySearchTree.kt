package validatebinarysearchtree

import common.structures.TreeNode

class ValidateBinarySearchTree {

    init {
        val codeChallenge = "\n" +
            "*************************** Validate Binary Search Tree ***************************\n" +
            "*                                                                                 *\n" +
            "* Given the root of a binary tree, determine if it is a valid binary search tree  *\n" +
            "* (BST).                                                                          *\n" +
            "*                                                                                 *\n" +
            "* A valid BST is defined as follows:                                              *\n" +
            "* -> The left subtree of a node contains only nodes with keys less than the       *\n" +
            "*    node's key.                                                                  *\n" +
            "* -> The right subtree of a node contains only nodes with keys greater than the   *\n" +
            "*    node's key.                                                                  *\n" +
            "* -> Both the left and right subtrees must also be binary search trees.           *\n" +
            "*                                                                                 *\n" +
            "* Example 1:                                                                      *\n" +
            "*    | Input: root = [2,1,3]                                                      *\n" +
            "*    | Output: true                                                               *\n" +
            "*                                                                                 *\n" +
            "* Example 2:                                                                      *\n" +
            "*    | Input: root = [5,1,4,null,null,3,6]                                        *\n" +
            "*    | Output: false                                                              *\n" +
            "*    | Explanation: The root node's value is 5 but its right child's value is 4.  *\n" +
            "*                                                                                 *\n" +
            "* Constraints:                                                                    *\n" +
            "*    | The number of nodes in the tree is in the range [1, 104].                  *\n" +
            "*    | -2^31 <= Node.val <= 2^31 - 1                                              *\n" +
            "***********************************************************************************\n"
        println(codeChallenge)
    }

    fun isValidBST(root: TreeNode?, printableTree: String): Boolean {
        printSolutionStart(printableTree)

        return checkTree(root, Long.MIN_VALUE, Long.MAX_VALUE).printSolutionEnd()
    }

    private fun checkTree(node: TreeNode?, minValue: Long, maxValue: Long): Boolean {
        // Base case: node is null, return true
        if (node == null) return true
        // If the current node is not between the min and max boundaries, then return false
        if (node.`val` !in minValue..maxValue) return false

        /*
            If not base case, continue verification:
                - The left subtree must be in the range: Int.MIN_VALUE until (currentNode.val - 1)
                - The right subtree must be in the range: (currentNode.val + 1) until INT.MAX_VALUE
         */
        return checkTree(node.left, minValue, node.`val`.toLong() - 1) &&
                checkTree(node.right, node.`val`.toLong() + 1, maxValue)
    }
}

private fun printSolutionStart(printableTree: String) {
    println("===================================================================================")
    println("- Input: root = $printableTree")
}

private fun Boolean.printSolutionEnd(): Boolean {
    println("- Output: $this")
    println("===================================================================================\n")
    return this
}