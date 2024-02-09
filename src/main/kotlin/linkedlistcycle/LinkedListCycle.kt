package linkedlistcycle

import common.structures.ListNode

/**
 * Solution implementing Floyd's cycle-finding algorithm
 *
 * @see <a href="https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare">Wiki article</a>
 */
class LinkedListCycle {

    init {
        val codeChallenge = "\n" +
            "********************* Linked List Cycle *********************\n" +
            "* Given head, the head of a linked list, determine if the   *\n" +
            "* linked list has a cycle in it.                            *\n" +
            "* There is a cycle in a linked list if there is some node   *\n" +
            "* in the list that can be reached again by continuously     *\n" +
            "* following the next pointer. Internally, pos is used to    *\n" +
            "* denote the index of the node that tail's next pointer is  *\n" +
            "* connected to. Note that pos is not passed as a parameter. *\n" +
            "*                                                           *\n" +
            "* Return true if there is a cycle in the linked list.       *\n" +
            "* Otherwise, return false.                                  *\n" +
            "*                                                           *\n" +
            "* Example 1:                                                *\n" +
            "*    | Input: head = [3,2,0,-4], pos = 1                    *\n" +
            "*    | Output: true                                         *\n" +
            "*    | Explanation: There is a cycle in the linked list,    *\n" +
            "*      where the tail connects to the 1st node (0-indexed). *\n" +
            "*                                                           *\n" +
            "* Example 2:                                                *\n" +
            "*    | Input: head = [1,2], pos = 0                         *\n" +
            "*    | Output: true                                         *\n" +
            "*    | Explanation: There is a cycle in the linked list,    *\n" +
            "*      where the tail connects to the 0th node.             *\n" +
            "*                                                           *\n" +
            "* Example 3:                                                *\n" +
            "*    | Input: head = [1], pos = -1                          *\n" +
            "*    | Output: false                                        *\n" +
            "*    | Explanation: There is no cycle in the linked list.   *\n" +
            "*                                                           *\n" +
            "* Constraints:                                              *\n" +
            "*    | The number of the nodes in the list is in the        *\n" +
            "*      range [0, 10^4].                                     *\n" +
            "*    | -10^5 <= Node.val <= 10^5                            *\n" +
            "*    | pos is -1 or a valid index in the linked-list.       *\n" +
            "*************************************************************\n"

        println(codeChallenge)
    }

    fun hasCycle(head: ListNode?, printableListForPresentation: String): Boolean {
        printSolutionStart(printableListForPresentation)

        // Edge case: Singly linked list is empty
        if (head == null) return false.printSolutionEnd()

        var slowerPointer = head
        var fasterPointer = head?.next

        while (slowerPointer != fasterPointer) {
            if (fasterPointer == null || fasterPointer?.next == null) {
                /*
                    In case that either 'fasterPointer' or 'fasterPointer.next' is null, then
                    it means that the linked list has a tail and it is not linked to any other node
                */
                return false.printSolutionEnd()
            }

            /*
                The idea is to have 2 runners, a slower (that moves 1 step at a time) and a faster one
                (that moves 2 steps at a time). If a cycle really exists, one time the faster runner
                will catch up with the slower
            */
            slowerPointer = slowerPointer?.next
            fasterPointer = fasterPointer?.next?.next
        }
        // If it exits the while loop, it means that slowerPointer == fasterPointer and the cycle was found
        return true.printSolutionEnd()
    }

}

private fun printSolutionStart(printableListForPresentation: String) {
    println("=============================================================")
    println("- Input: $printableListForPresentation")
}

private fun Boolean.printSolutionEnd(): Boolean {
    println("- Output: $this")
    println("=============================================================\n")
    return this
}