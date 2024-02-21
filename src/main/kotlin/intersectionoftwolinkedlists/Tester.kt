package intersectionoftwolinkedlists

import common.structures.ListNode

fun main() {

    val commonForCaseOne = ListNode(8).apply {
        next = ListNode(4).apply { next = ListNode(5) }
    }
    val commonForCaseTwo = ListNode(2).apply { next = ListNode(4) }

    val caseOneListA = ListNode(4).apply { next = ListNode(1).apply { next = commonForCaseOne } }
    val caseOneListB = ListNode(5).apply { next = ListNode(5).apply { next = ListNode(1).apply { next = commonForCaseOne } } }

    val caseTwoListA = ListNode(1).apply { next = ListNode(9).apply { next = ListNode(1).apply { next = commonForCaseTwo } } }
    val caseTwoListB = ListNode(1).apply { next = commonForCaseTwo }

    val caseThreeListA = ListNode(2).apply { next = ListNode(6).apply { next = ListNode(4) } }
    val caseThreeListB = ListNode(1).apply { next = ListNode(5) }

    val solution = IntersectionOfTwoLinkedLists()

    solution.getIntersectionNode(caseOneListA, caseOneListB)
    solution.getIntersectionNode(caseTwoListA, caseTwoListB)
    solution.getIntersectionNode(caseThreeListA, caseThreeListB)
}