package firstbadversion

class FirstBadVersion: VersionControl() {

    init {
        val codeChallenge =
            "\n" +
            "************************ First Bad Version ************************\n" +
            "* You are a product manager and currently leading a team to       *\n" +
            "* develop a new product. Unfortunately, the latest version of your*\n" +
            "* product fails the quality check. Since each version is developed*\n" +
            "* based on the previous version, all the versions after a bad     *\n" +
            "* version are also bad.                                           *\n" +
            "* Suppose you have n versions [1, 2, ..., n] and you want to find *\n" +
            "* out the first bad one, which causes all the following ones to   *\n" +
            "* be bad.                                                         *\n" +
            "* You are given an API bool isBadVersion(version) which returns   *\n" +
            "* whether version is bad. Implement a function to find the first  *\n" +
            "* bad version. You should minimize the number of calls to the API.*\n" +
            "*                                                                 *\n" +
            "* Example 1:                                                      *\n" +
            "*    | Input: n = 5, bad = 4                                      *\n" +
            "*    | Output: 4                                                  *\n" +
            "*    | Explanation:                                               *\n" +
            "*          call isBadVersion(3) -> false                          *\n" +
            "*          call isBadVersion(5) -> true                           *\n" +
            "*          call isBadVersion(4) -> true                           *\n" +
            "*          Then 4 is the first bad version.                       *\n" +
            "*                                                                 *\n" +
            "* Example 2                                                       *\n" +
            "*    | Input: n = 1, bad = 1                                      *\n" +
            "*    | Output: 1                                                  *\n" +
            "*******************************************************************\n"

        println(codeChallenge)
    }
    override fun firstBadVersion(n: Int): Int {
        // Edge case - version less or equal to zero
        if (n <= 0) {
            return 0
        }
        // Edge case - only one version to check
        if (n == 1) {
            return if (isBadVersion(1)) {
                1
            } else {
                0
            }
        }

        var leftBoundary = 1
        var rightBoundary = n
        var pivot = (rightBoundary / 2) + 1

        while (pivot in leftBoundary..rightBoundary) {
            if (isBadVersion(pivot)) {
                // if 'pivot - 1' == 0, or isBadVersion from 'pivot - 1' is false, then we found the first bad version
                // else adjust the pivot to the left (keep leftBoundary and adjust pivot and rightBoundary)
                if (pivot - 1 == 0 || !isBadVersion(pivot - 1)) {
                    return pivot
                } else {
                    val offset = (pivot - leftBoundary + 1) / 2
                    val temp = pivot
                    pivot -= offset
                    rightBoundary = temp
                }
            } else {
                // Adjust pivot to the right (keep rightBoundary and adjust pivot and leftBoundary)
                val offset = (rightBoundary - pivot + 1) / 2
                val temp = pivot
                pivot += offset
                leftBoundary = temp
            }
        }

        return 0
    }
}