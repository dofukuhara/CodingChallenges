package lowestcommonancestor

import common.structures.TreeNode

class LowestCommonAncestor {
    init {
        val codeChallenge = "\n" +
                "********************* Lowest Common Ancestor of a Binary Tree *********************\n" +
                "*                                                                                 *\n" +
                "* Given a binary tree, find the lowest common ancestor (LCA) of two given nodes   *\n" +
                "* in the tree.                                                                    *\n" +
                "*                                                                                 *\n" +
                "* According to the definition of LCA on Wikipedia: \"The lowest common ancestor is *\n" +
                "* defined between two nodes p and q as the lowest node in T that has both p and q *\n" +
                "* as descendants (where we allow a node to be a descendant of itself).\"           *\n" +
                "*                                                                                 *\n" +
                "* Example 1:                                                                      *\n" +
                "*    | Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1                  *\n" +
                "*    | Output: 3                                                                  *\n" +
                "*    | Explanation: The LCA of nodes 5 and 1 is 3.                                *\n" +
                "*                                                                                 *\n" +
                "* Example 2:                                                                      *\n" +
                "*    | Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4                  *\n" +
                "*    | Output: 5                                                                  *\n" +
                "*    | Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a          *\n" +
                "*                   descendant of itself according to the LCA definition.         *\n" +
                "*                                                                                 *\n" +
                "* Example 3:                                                                      *\n" +
                "*    | Input: root = [1,2], p = 1, q = 2                                          *\n" +
                "*    | Output: 1                                                                  *\n" +
                "*                                                                                 *\n" +
                "* Constraints:                                                                    *\n" +
                "*    | The number of nodes in the tree is in the range [2, 10^5].                 *\n" +
                "*    | -10^9 <= Node.val <= 10^9                                                  *\n" +
                "*    | All Node.val are unique.                                                   *\n" +
                "*    | p != q                                                                     *\n" +
                "*    | p and q will exist in the tree.                                            *\n" +
                "***********************************************************************************\n"

        println(codeChallenge)
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?, treeRepresentationForDebug: String = ""): TreeNode? {
        printSolutionStart(treeRepresentationForDebug, p?.`val`, q?.`val`)

        return recursionLowestCommonAncestor(root, p, q).printSolutionEnd()
    }

    private fun recursionLowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // Base case: either current node is null or it is node 'p' or 'q', then return root
        if (root == null || p == root || q == root) return root

        // recursively search 'p' and 'q' from the left and right side of the tree
        val left = recursionLowestCommonAncestor(root.left, p, q)
        val right = recursionLowestCommonAncestor(root.right, p, q)

        // if left and right are not null, it means that based on this current 'root' node, 'p' and 'q' are
        // in the opposite sides starting from this 'root' node, so return it
        if (left != null && right != null) return root

        // If right is not null, it means that both 'p' and 'q' are on the right side of the queue, so return it
        // Otherwise, it means that 'p' and 'q' are both on the left side, so return it
        return right ?: left
    }
}

private fun printSolutionStart(treeRepresentationForDebug: String, pValue: Int?, qValue: Int?) {
    println("===================================================================================")
    println("- Input: root = $treeRepresentationForDebug, p = $pValue, q = $qValue")
}

private fun TreeNode?.printSolutionEnd():TreeNode? {
    println("- Output: ${this?.`val`}")
    println("===================================================================================\n")
    return this
}