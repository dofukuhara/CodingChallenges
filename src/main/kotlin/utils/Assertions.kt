package utils

import common.structures.GraphNode
import common.structures.TreeNode

fun Boolean.assertWith(expected: Boolean): Boolean {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$this'")
    }
    return this
}

fun Int.assertWith(expected: Int): Int {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$this'")
    }
    return this
}

fun String.assertWith(expected: String): String {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$this'")
    }
    return this
}

fun Array<IntArray>.assertWith(expected: String): Array<IntArray> {
    val actualResult = this.printableArrayOfIntArray()
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$actualResult'")
    }
    return this
}

fun IntArray.assertWith(expected: String): IntArray {
    val actualResult = this.printableIntArray()
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$actualResult'")
    }
    return this
}

fun List<List<Int>>.assertWith(expected: String): List<List<Int>> {
    if (this.toString() != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$this'")
    }
    return this
}

fun TreeNode?.assertWith(expected: Int): TreeNode? {
    val actualResult = this?.`val`
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$actualResult'")
    }
    return this
}

fun GraphNode?.assertWith(expected: String): GraphNode? {
    val actualResult = this.printableGraph()
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: '$expected'\n- Actual:   '$actualResult'")
    }
    return this
}