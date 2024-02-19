package lowestcommonancestor

import common.structures.TreeNode
import utils.assertWith

fun main() {
    val solution = LowestCommonAncestor()

    val qNodeFour = TreeNode(4)
    val pNodeFive = TreeNode(5).apply {
        left = TreeNode(6)
        right = TreeNode(2).apply {
            left = TreeNode(7)
            right = qNodeFour
        }
    }
    val qNodeOne =  TreeNode(1).apply {
        left = TreeNode(0)
        right = TreeNode(8)
    }

    val treeForOneAndTwo = TreeNode(3).apply {
        left = pNodeFive
        right = qNodeOne
    }

    solution.lowestCommonAncestor(
        root = treeForOneAndTwo,
        p = pNodeFive,
        q = qNodeOne,
        treeRepresentationForDebug = "[3,5,1,6,2,0,8,null,null,7,4]"
    ).assertWith(3)

    solution.lowestCommonAncestor(
        root = treeForOneAndTwo,
        p = pNodeFive,
        q = qNodeFour,
        treeRepresentationForDebug = "3,5,1,6,2,0,8,null,null,7,4]"
    ).assertWith(5)

    val qNodeTwo = TreeNode(2)
    val pNodeOne = TreeNode(1).apply { left = qNodeTwo }
    val treeForThree = pNodeOne
    solution.lowestCommonAncestor(
        root = treeForThree,
        p = pNodeOne,
        q = qNodeTwo,
        treeRepresentationForDebug = "[1,2]"
    ).assertWith(1)
}