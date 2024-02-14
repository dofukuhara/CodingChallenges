package lowestcommonancestorbst

import common.structures.TreeNode
import utils.assertWith

fun main() {
    val solution = LowestCommonAncestorBST()

    val qForCaseTwo = TreeNode(4).apply {
        left = TreeNode(3)
        right = TreeNode(5)
    }
    val pForCaseOneAndTwo = TreeNode(2).apply {
        left = TreeNode(0)
        right = qForCaseTwo
    }
    val qForCaseOne = TreeNode(8).apply {
        left = TreeNode(7)
        right = TreeNode(9)
    }
    val caseOneAndTwoTree = TreeNode(6).apply {
        left = pForCaseOneAndTwo
        right = qForCaseOne
    }
    val qForCaseThree = TreeNode(1)
    val pForCaseThree =  TreeNode(2).apply {
        left = qForCaseThree
    }

    solution.lowestCommonAncestor(
        root = caseOneAndTwoTree, p = pForCaseOneAndTwo, q = qForCaseOne,
        treeRepresentationForDebug = "[6,2,8,0,4,7,9,null,null,3,5]"
    ).assertWith(6)
    solution.lowestCommonAncestor(
        root = caseOneAndTwoTree, p = pForCaseOneAndTwo, q = qForCaseTwo,
        treeRepresentationForDebug = "[6,2,8,0,4,7,9,null,null,3,5]"
    ).assertWith(2)
    solution.lowestCommonAncestor(
        root = pForCaseThree, p = pForCaseThree, q = qForCaseThree,
        treeRepresentationForDebug = "[2,1]"
    ).assertWith(2)

}