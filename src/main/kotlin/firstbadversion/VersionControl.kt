package firstbadversion

abstract class VersionControl {
    private var badVersion: Int = 1

    fun setNewBadVersion(newBadVersion: Int): VersionControl {
        badVersion = newBadVersion
        return this
    }

    fun isBadVersion(version: Int): Boolean {
        return version >= badVersion
    }

    // Method exposed only for debugging purpose
    fun getBadVersion() = badVersion

    abstract fun firstBadVersion(n: Int): Int
}