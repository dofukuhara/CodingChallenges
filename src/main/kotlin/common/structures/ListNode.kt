package common.structures

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return if (next != null) {
            "$`val` -> $next"
        } else {
            "$`val`"
        }
    }
}