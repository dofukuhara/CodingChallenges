package lowestcommonancestorbst

import common.structures.TreeNode

class LowestCommonAncestorBST {

    init {
        val codeChallenge = "\n" +
            "****** Lowest Common Ancestor of a Binary Search Tree ******\n" +
            "* Given a binary search tree (BST), find the lowest common *\n" +
            "* ancestor (LCA) node of two given nodes in the BST.       *\n" +
            "*                                                          *\n" +
            "* According to the definition of LCA on Wikipedia: 'The    *\n" +
            "* lowest common ancestor is defined between two nodes p    *\n" +
            "* and q as the lowest node in T that has both p and q as   *\n" +
            "* descendants (where we allow a node to be a descendant of *\n" +
            "* itself).'                                                *\n" +
            "*                                                          *\n" +
            "* Example 1:                                               *\n" +
            "*    | Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, *\n" +
            "*             q = 8                                        *\n" +
            "*    | Output: 6                                           *\n" +
            "*    | Explanation: The LCA of nodes 2 and 8 is 6.         *\n" +
            "*                                                          *\n" +
            "* Example 2:                                               *\n" +
            "*    | Input: root = [6,2,8,0,4,7,9,null,null,3,5],        *\n" +
            "*             p = 2, q = 4                                 *\n" +
            "*    | Output: 2                                           *\n" +
            "*    | Explanation: The LCA of nodes 2 and 4 is 2, since a *\n" +
            "*                   node can be a descendant of itself     *\n" +
            "*                   according to the LCA definition.       *\n" +
            "*                                                          *\n" +
            "* Example 3:                                               *\n" +
            "*    | Input: root = [2,1], p = 2, q = 1                   *\n" +
            "*    | Output: 2                                           *\n" +
            "*                                                          *\n" +
            "* Constraints:                                             *\n" +
            "*    | The number of nodes in the tree is in the range     *\n" +
            "*      [2, 10^5].                                          *\n" +
            "*    | -10^9 <= Node.val <= 10^9                           *\n" +
            "*    | All Node.val are unique.                            *\n" +
            "*    | p != q                                              *\n" +
            "*    | p and q will exist in the BST.                      *\n" +
            "************************************************************\n"

        println(codeChallenge)
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?, treeRepresentationForDebug: String): TreeNode? {
        printSolutionStart(treeRepresentationForDebug, p?.`val`, q?.`val`)

        if (root == null || p == null || q == null) return null.printSolutionEnd()
        var currentNode = root

        while (currentNode != null) {
            currentNode = when {
                p.`val` < currentNode.`val` && q.`val` < currentNode.`val` -> currentNode.left
                p.`val` > currentNode.`val` && q.`val` > currentNode.`val` -> currentNode.right
                else -> return currentNode.printSolutionEnd()
            }
        }

        return null.printSolutionEnd()
    }

    private fun printSolutionStart(treeRepresentationForDebug: String, pValue: Int?, qValue: Int?) {
        println("============================================================")
        println("- Input: root = $treeRepresentationForDebug, p = $pValue, q = $qValue")
    }

    private fun TreeNode?.printSolutionEnd():TreeNode? {
        println("- Output: ${this?.`val`}")
        println("============================================================\n")
        return this
    }
}