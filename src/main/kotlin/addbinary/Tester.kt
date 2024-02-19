package addbinary

import common.structures.TreeNode
import utils.assertWith

fun main() {
//    val solution = AddBinary()

//    solution.addBinary("11", "1").assertWith("100")
//    solution.addBinary("1010", "1011").assertWith("10101")

    println(
    isValidBST(TreeNode(3).apply {
        left = TreeNode(1)
        right= TreeNode(5)
    })
    )
}


//fun isValidBST(root: TreeNode?): Boolean = isValidBSTInternal(root)

private fun isValidBSTInternal(
    root: TreeNode?,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE,
): Boolean {
    if (root == null) return true
    if (root.`val` !in min..max) return false
    return isValidBSTInternal(root.left, min, root.`val` - 1)
            && isValidBSTInternal(root.right, root.`val` + 1, max)
}


fun isValidBST(root: TreeNode?): Boolean {

    val result = checkTree(root)
    println(result)
    return result != null
    // if (root == null) return true

    // val currentNodeValue = root.`val`
    // val leftNodeValue = root.left?.`val`
    // val rightNodeValue = root.right?.`val`

    // if (
    //     leftNodeValue != null && leftNodeValue >= currentNodeValue ||
    //     rightNodeValue != null && rightNodeValue <= currentNodeValue
    // ) {
    //     return false
    // }

    // val leftCheck = isValidBST(root.left)
    // val rightCheck = isValidBST(root.right)

    // return leftCheck && rightCheck
}

private fun checkTree(node: TreeNode?): List<Int>? {
    if (node == null) return emptyList()

    val currentNodeValue = node.`val`
    val leftNodeValue = node.left?.`val`
    val rightNodeValue = node.right?.`val`

    if (
        leftNodeValue != null && leftNodeValue >= currentNodeValue ||
        rightNodeValue != null && rightNodeValue <= currentNodeValue
    ) {
        return null
    }

    val leftChildrenAsList = checkTree(node.left)
    val rightChildrenAsList = checkTree(node.right)

    if (
        leftChildrenAsList == null ||
        rightChildrenAsList == null ||
        leftChildrenAsList.filter { it >= currentNodeValue }.size > 0 ||
        rightChildrenAsList.filter { it <= currentNodeValue }.size > 0
    ) return null

    return leftChildrenAsList + rightChildrenAsList + listOf(currentNodeValue)
}