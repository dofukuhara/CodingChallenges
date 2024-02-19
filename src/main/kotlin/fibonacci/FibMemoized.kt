package fibonacci

class FibMemoized: Fibonacci {
    override fun getValue(target: Int): Int {
        val result: ArrayList<Int> = arrayListOf(0, 1)
        fibBacktrack(target, result)

        println("fibMemoized of $target is [${result[target]}]")

        return result[target]
    }

    private fun fibBacktrack(fibIndex: Int, resultCache: ArrayList<Int>): Int {
        if (resultCache.getOrNull(fibIndex) != null) {
            return resultCache[fibIndex]
        }

        resultCache.add(
            index = fibIndex,
            element = fibBacktrack(fibIndex-1, resultCache) + fibBacktrack(fibIndex-2, resultCache)
        )
        return resultCache[fibIndex]
    }
}