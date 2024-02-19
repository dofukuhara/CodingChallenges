package fibonacci

import utils.assertWith

fun main() {
    println("--------------------------")
    val initFibMemoizedTime = System.currentTimeMillis()
    val fibMemoized = FibMemoized()
    with (fibMemoized) {
        getValue(0).assertWith(0)
        getValue(1).assertWith(1)
        getValue(2).assertWith(1)
        getValue(3).assertWith(2)
        getValue(4).assertWith(3)
        getValue(5).assertWith(5)
        getValue(6).assertWith(8)
        getValue(7).assertWith(13)
        getValue(8).assertWith(21)
        getValue(9).assertWith(34)
        getValue(10).assertWith(55)
        getValue(38).assertWith(39088169)
        getValue(45).assertWith(1134903170)

    }
    val finalFibMemoizedTime = System.currentTimeMillis()
    println("\nFibMemoized took ${finalFibMemoizedTime - initFibMemoizedTime}ms")
    println("--------------------------")
    val initFibRecursiveTime = System.currentTimeMillis()
    val fibRecursive = FibRecursive()
    with(fibRecursive) {
        getValue(0).assertWith(0)
        getValue(1).assertWith(1)
        getValue(2).assertWith(1)
        getValue(3).assertWith(2)
        getValue(4).assertWith(3)
        getValue(5).assertWith(5)
        getValue(6).assertWith(8)
        getValue(7).assertWith(13)
        getValue(8).assertWith(21)
        getValue(9).assertWith(34)
        getValue(10).assertWith(55)
        getValue(38).assertWith(39088169)
        // Will not run this last case, it recursive approach is not performant enough for it :(
//        getValue(45).assertWith(1134903170)
    }
    val finalFibRecursiveTime = System.currentTimeMillis()
    println("\nFibRecursive took ${finalFibRecursiveTime - initFibRecursiveTime}ms")
    println("--------------------------")
    val initFibIterativeTime = System.currentTimeMillis()
    val fibIterative = FibIterative()
    with(fibIterative) {
        getValue(0).assertWith(0)
        getValue(1).assertWith(1)
        getValue(2).assertWith(1)
        getValue(3).assertWith(2)
        getValue(4).assertWith(3)
        getValue(5).assertWith(5)
        getValue(6).assertWith(8)
        getValue(7).assertWith(13)
        getValue(8).assertWith(21)
        getValue(9).assertWith(34)
        getValue(10).assertWith(55)
        getValue(38).assertWith(39088169)
        getValue(45).assertWith(1134903170)

    }
    val finalFibIterativeTime = System.currentTimeMillis()
    println("\nFibIterative took ${finalFibIterativeTime - initFibIterativeTime}ms")
    println("--------------------------")
}
