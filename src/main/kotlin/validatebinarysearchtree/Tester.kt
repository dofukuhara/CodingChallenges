package validatebinarysearchtree

import common.structures.TreeNode
import utils.assertWith

fun main() {
    val solution = ValidateBinarySearchTree()

    solution.isValidBST(
        root = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3)
         },
        printableTree = "[2,1,3]"
    ).assertWith(true)

    solution.isValidBST(
        root = TreeNode(5).apply {
            left = TreeNode(1)
            right = TreeNode(4).apply {
                left = TreeNode(3)
                right = TreeNode(6)
            }
        },
        printableTree = "[5,1,4,null,null,3,6]"
    ).assertWith(false)
}