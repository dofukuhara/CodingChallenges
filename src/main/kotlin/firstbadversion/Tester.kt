package firstbadversion

fun main() {
    val solution = FirstBadVersion()

    println( solution.setNewBadVersion(newBadVersion = 4).firstBadVersion(n = 5) )
    println( solution.setNewBadVersion(newBadVersion = 1).firstBadVersion(n = 1) )
    println( solution.setNewBadVersion(newBadVersion = 2147483647).firstBadVersion(n = 2147483647) )

}