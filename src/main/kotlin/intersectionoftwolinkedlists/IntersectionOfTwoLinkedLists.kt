package intersectionoftwolinkedlists

import common.structures.ListNode

class IntersectionOfTwoLinkedLists {
    init {
        val codeChallenge = "\n" +
            "************************* Intersection of Two Linked Lists *************************\n" +
            "*                                                                                  *\n" +
            "* Given the heads of two singly linked-lists headA and headB, return the node at   *\n" +
            "* which the two lists intersect. If the two linked lists have no intersection at   *\n" +
            "* all, return null.                                                                *\n" +
            "*                                                                                  *\n" +
            "* Example 1:                                                                       *\n" +
            "*             listA:       (4) → (1) ↘                                             *\n" +
            "*                                     (8) → (4) → (5)                              *\n" +
            "*             listB: (5) → (6) → (1) ↗                                             *\n" +
            "*                                                                                  *\n" +
            "*    | Input: listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]                           *\n" +
            "*    | Output: Intersected at '8'                                                  *\n" +
            "*    | Explanation: The intersected node's value is 8 (note that this must not be  *\n" +
            "*            0 if the two lists intersect).                                        *\n" +
            "*                From the head of A, it reads as [4,1,8,4,5]. From the head of B,  *\n" +
            "*            it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected   *\n" +
            "*            node in A; There are 3 nodes before the intersected node in B.        *\n" +
            "*                - Note that the intersected node's value is not 1 because the     *\n" +
            "*            nodes with value 1 in A and B (2nd node in A and 3rd node in B) are   *\n" +
            "*            different node references. In other words, they point to two different*\n" +
            "*            locations in memory, while the nodes with value 8 in A and B (3rd node*\n" +
            "*            in A and 4th node in B) point to the same location in memory.         *\n" +
            "*                                                                                  *\n" +
            "* Example 2:                                                                       *\n" +
            "*             listA: (1) → (9) → (1) ↘                                             *\n" +
            "*                                     (2) → (4)                                    *\n" +
            "*             listB:             (3) ↗                                             *\n" +
            "*                                                                                  *\n" +
            "*    | Input: listA = [1,9,1,2,4], listB = [3,2,4]                                 *\n" +
            "*    | Output: Intersected at '2'                                                  *\n" +
            "*    | Explanation: The intersected node's value is 2 (note that this must not be  *\n" +
            "*          0 if the two lists intersect).                                          *\n" +
            "*                From the head of A, it reads as [1,9,1,2,4]. From the head of B,  *\n" +
            "*          it reads as [3,2,4]. There are 3 nodes before the intersected node in   *\n" +
            "*          A; There are 1 node before the intersected node in B.                   *\n" +
            "*                                                                                  *\n" +
            "* Example 3:                                                                       *\n" +
            "*             listA: (2) → (6) → (4)                                               *\n" +
            "*             listB: (1) → (5)                                                     *\n" +
            "*                                                                                  *\n" +
            "*    | Input: listA = [2,6,4], listB = [1,5]                                       *\n" +
            "*    | Output: No intersection                                                     *\n" +
            "*    | Explanation: From the head of A, it reads as [2,6,4]. From the head of B,   *\n" +
            "*          it reads as [1,5]. Since the two lists do not intersect, intersectVal   *\n" +
            "*          must be 0, while skipA and skipB can be arbitrary values.               *\n" +
            "*    | Explanation: The two lists do not intersect, so return null.                *\n" +
            "*                                                                                  *\n" +
            "* Constraints:                                                                     *\n" +
            "*    | The number of nodes of listA is in the m.                                   *\n" +
            "*    | The number of nodes of listB is in the n.                                   *\n" +
            "*    | 1 <= m, n <= 3 * 10^4                                                       *\n" +
            "*    | 1 <= Node.val <= 10^5                                                       *\n" +
            "*    | 0 <= skipA < m                                                              *\n" +
            "*    | 0 <= skipB < n                                                              *\n" +
            "*    | intersectVal is 0 if listA and listB do not intersect.                      *\n" +
            "*    | intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.  *\n" +
            "*                                                                                  *\n" +
            "* Follow up: Could you write a solution that runs in O(m + n) time and use only    *\n" +
            "* O(1) memory?                                                                     *\n" +
            "************************************************************************************\n"

        println(codeChallenge)
    }

    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        printSolutionStart(headA, headB)

        // return brutalForceSolution(headA, headB).printSolutionEnd()

        return betterSolution(headA, headB).printSolutionEnd()
    }

    private fun betterSolution(headA:ListNode?, headB:ListNode?):ListNode? {
        // Get the size of both lists
        val listASize = getListSize(headA)
        val listBSize = getListSize(headB)
        // Initialize the list runners
        var listARunner = headA
        var listBRunner = headB

        // If listA is longer than listB, advance it, so that the distance from listARunner until the end of the list
        // will be the same distance as from listBRunner until the end
        if (listASize > listBSize) {
            repeat (listASize - listBSize) {
                listARunner = listARunner?.next
            }
        }
        // Otherwise, apply the same logic, but for the listBRunner
        if (listBSize > listASize) {
            repeat (listBSize - listASize) {
                listBRunner = listBRunner?.next
            }
        }

        // With listARunner and listBRunner aligned, traverse through the lists at the same time until
        // we either find a common node or result null
        while (listARunner != null) {
            if (listARunner == listBRunner) {
                return listARunner
            }
            listARunner = listARunner?.next
            listBRunner = listBRunner?.next
        }

        return null
    }

    private fun getListSize(root: ListNode?): Int {
        var size = 0
        var runner = root
        while (runner != null) {
            runner = runner.next
            size++
        }
        return size
    }

    private fun brutalForceSolution(headA:ListNode?, headB:ListNode?):ListNode? {
        // Build up a cache with all the nodes from ListA
        val firstListCache = mutableListOf<ListNode>()
        var headArunner = headA
        while (headArunner != null) {
            firstListCache.add(headArunner)
            headArunner = headArunner.next
        }

        // While traversing through ListB, check if this node address is found in the ListA cache
        var headBrunner = headB
        while (headBrunner != null) {
            if (firstListCache.contains(headBrunner)) {
                return headBrunner
            }
            headBrunner = headBrunner.next
        }

        return null
    }
}

private fun printSolutionStart(headA: ListNode?, headB: ListNode?) {
    println("====================================================================================")
    println("- Input: ")
    println("  | listA = [$headA]")
    println("  | listA = [$headB]")
}

private fun ListNode?.printSolutionEnd(): ListNode? {
    val message = if (this == null) {
        "- Output: No intersection"
    } else {
        "- Output: Intersected at '${this.`val`}[${this.hashCode()}]'"
    }
    println(message)
    println("====================================================================================\n")
    return this
}