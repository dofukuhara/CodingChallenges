package firstbadversion

import utils.assertWith

fun main() {
    val solution = FirstBadVersion()

    solution.setNewBadVersion(newBadVersion = 4).firstBadVersion(n = 5).assertWith(4)
    solution.setNewBadVersion(newBadVersion = 1).firstBadVersion(n = 1).assertWith(1)
    solution.setNewBadVersion(newBadVersion = 2147483647).firstBadVersion(n = 2147483647).assertWith(2147483647)

}