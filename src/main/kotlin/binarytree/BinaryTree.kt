package binarytree

import common.structures.TreeNode

class BinaryTree {
    fun traverseBinaryTreeBfs(root: TreeNode?) {
        val treeTraversalList = mutableListOf<BfsNode?>().apply {
            add(BfsNode.Node(root))
            add(BfsNode.LevelSeparator)
        }

        var printLevelInfo = true
        var level = 1

        while (treeTraversalList.isNotEmpty()) {
            val printTree = StringBuilder()
            when (val currentNode = treeTraversalList.removeFirst()) {
                is BfsNode.Node -> {
                    if (printLevelInfo) {
                        printLevelInfo = false
                        print("Tree Level [$level] -> ")
                    }
                    printTree.append(currentNode.node?.`val`)
                    printTree.append(" ")

                    currentNode.node?.left?.let {
                        treeTraversalList.add(BfsNode.Node(it))
                    }
                    currentNode.node?.right?.let {
                        treeTraversalList.add(BfsNode.Node(it))
                    }
                }
                BfsNode.LevelSeparator -> {
                    // Only add another Level Separator if the queue is not empty
                    if (treeTraversalList.isNotEmpty()) {
                        treeTraversalList.add(BfsNode.LevelSeparator)
                        level += 1
                        printLevelInfo = true
                        printTree.append("\n")
                    }
                }
                else -> Unit
            }
            print(printTree.toString())
        }
    }

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)

        return maxOf(leftDepth, rightDepth) + 1
    }
}

sealed class BfsNode {
    class Node(val node: TreeNode?): BfsNode()
    object LevelSeparator : BfsNode()
}