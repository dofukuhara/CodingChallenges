package utils

import common.structures.TreeNode

fun Boolean.assertWith(expected: Boolean) {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $this")
    }
}

fun Int.assertWith(expected: Int) {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $this")
    }
}

fun String.assertWith(expected: String) {
    if (this != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $this")
    }
}

fun Array<IntArray>.assertWith(expected: String) {
    val actualResult = this.printableArrayOfIntArray()
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $actualResult")
    }
}

fun IntArray.assertWith(expected: String) {
    val actualResult = this.printableIntArray()
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $actualResult")
    }
}

fun List<List<Int>>.assertWith(expected: String) {
    if (this.toString() != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $this")
    }
}

fun TreeNode?.assertWith(expected: Int) {
    val actualResult = this?.`val`
    if (actualResult != expected) {
        throw TestCaseAssertFail("\nTest case fail\n- Expected: $expected\n- Actual:   $actualResult")
    }
}