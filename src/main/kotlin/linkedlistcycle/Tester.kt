package linkedlistcycle

import common.structures.ListNode
import utils.assertWith

fun main() {
    val solution = LinkedListCycle()

    val testCaseOne = ListNode(3).apply {
        next = ListNode(2). apply {
            next = ListNode(0).apply {
                next = ListNode(-4)
            }
        }
    }
    testCaseOne.next?.next?.next?.next = testCaseOne.next
    solution.hasCycle(testCaseOne, "head = [3,2,0,-4], pos= 1").assertWith(true)

    val testCaseTwo = ListNode(1).apply {
        next = ListNode(2)
    }
    testCaseTwo.next?.next = testCaseTwo
    solution.hasCycle(testCaseTwo , "head = [1,2], pos = 0").assertWith(true)

    val testCaseThree = ListNode(-1)
    solution.hasCycle(testCaseThree, "head = [1], pos = -1").assertWith(false)

    val testCaseFour: ListNode? = null
    solution.hasCycle(testCaseFour, "head = [], pos = -1").assertWith(false)
}