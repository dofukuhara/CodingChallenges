package fibonacci

class FibIterative : Fibonacci {
    override fun getValue(target: Int): Int {
        val result = fibIt(target)

        println("fibIterative of $target is [$result]")

        return result
    }

    private fun fibIt(target: Int): Int {
        if (target == 0 || target == 1) return target
        var accumulatorFirst = 0
        var accumulatorSecond = 1
        var result = 1

        for (iteration in 2 .. target) {
            result = accumulatorFirst + accumulatorSecond
            accumulatorFirst = accumulatorSecond
            accumulatorSecond = result
        }

        return result
    }
}