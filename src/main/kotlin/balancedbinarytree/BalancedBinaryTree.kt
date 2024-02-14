package balancedbinarytree

import common.structures.TreeNode
import kotlin.math.abs

class BalancedBinaryTree {

    init {
        val codeChallenge = "\n" +
            "******************* Balanced Binary Tree *******************\n" +
            "* Given a binary tree, determine if it is height-balanced. *\n" +
            "*                                                          *\n" +
            "* A height-balanced binary tree is a binary tree in which  *\n" +
            "* the depth of the two subtrees of every node never        *\n" +
            "* differs by more than one.                                *\n" +
            "*                                                          *\n" +
            "* Example 1:                                               *\n" +
            "*    | Input: root = [3,9,20,null,null,15,7]               *\n" +
            "*    | Output: true                                        *\n" +
            "*                                                          *\n" +
            "* Example 2:                                               *\n" +
            "*    | Input: root = [1,2,2,3,3,null,null,4,4]             *\n" +
            "*    | Output: false                                       *\n" +
            "*                                                          *\n" +
            "* Example 3:                                               *\n" +
            "*    | Input: root = []                                    *\n" +
            "*    | Output: true                                        *\n" +
            "*                                                          *\n" +
            "* Constraints:                                             *\n" +
            "*    | The number of nodes in the tree is in the range     *\n" +
            "*      [0, 5000].                                          *\n" +
            "*    | -10^4 <= Node.val <= 10^4                           *\n" +
            "************************************************************\n"

        println(codeChallenge)
    }

    fun isBalanced(root: TreeNode?, treeRepresentationForDebug: String): Boolean {
        printSolutionStart(treeRepresentationForDebug)

        if (root == null) return true

        val isBalanced = getHeight(node = root)

        return (isBalanced != -1).printSolutionEnd()
    }

    private fun getHeight(node: TreeNode?): Int {
        if (node == null) return 0

        val leftSideSize = getHeight(node.left)
        val rightSideSize = getHeight(node.right)

        if (leftSideSize == -1 || rightSideSize == -1) return -1
        if (abs(leftSideSize - rightSideSize) > 1) return -1
        return maxOf(leftSideSize, rightSideSize) + 1
    }

    private fun printSolutionStart(treeRepresentationForDebug: String) {
        println("============================================================")
        println("- Input: $treeRepresentationForDebug")
    }

    private fun Boolean.printSolutionEnd(): Boolean {
        println("- Output: $this")
        println("============================================================\n")
        return this
    }
}