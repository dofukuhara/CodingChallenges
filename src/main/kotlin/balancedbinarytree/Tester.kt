package balancedbinarytree

import common.structures.TreeNode
import utils.assertWith

fun main() {
    val solution = BalancedBinaryTree()

    solution.isBalanced(
        root = TreeNode(3).apply {
            left = TreeNode(9)
            right = TreeNode(20).apply {
                left = TreeNode(15)
                right = TreeNode(7)
            }
        },
        treeRepresentationForDebug = "[3,9,20,null,null,15,7]"
    ).assertWith(true)

    solution.isBalanced(
        root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(3).apply {
                    left = TreeNode(4)
                    right = TreeNode(4)
                }
                right = TreeNode(3)
            }
            right = TreeNode(2)
        },
        treeRepresentationForDebug = "[1,2,2,3,3,null,null,4,4]"
    ).assertWith(false)

    solution.isBalanced(root = null, treeRepresentationForDebug = "[]").assertWith(true)
}