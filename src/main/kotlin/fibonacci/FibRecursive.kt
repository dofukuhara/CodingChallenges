package fibonacci

class FibRecursive: Fibonacci {
    override fun getValue(target: Int): Int {
        val result = fibRec(target)

        println("fibRecursive of $target is [$result]")

        return result
    }

    private fun fibRec(target: Int) : Int {
        if (target == 0 || target == 1) return target

        return fibRec(target - 1) + fibRec(target - 2)
    }
}