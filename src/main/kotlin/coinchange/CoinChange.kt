package coinchange

import utils.printableIntArray

class CoinChange {
    init {
        val codeChallenge = "\n" +
            "************************** Coin Change **************************\n" +
            "* You are given an integer array coins representing coins of    *\n" +
            "* different denominations and an integer amount representing a  *\n" +
            "* total amount of money.                                        *\n" +
            "*                                                               *\n" +
            "* Return the fewest number of coins that you need to make up    *\n" +
            "* that amount. If that amount of money cannot be made up by any *\n" +
            "* combination of the coins, return -1.                          *\n" +
            "*                                                               *\n" +
            "* You may assume that you have an infinite number of each kind  *\n" +
            "* of coin.                                                      *\n" +
            "*                                                               *\n" +
            "* Example 1:                                                    *\n" +
            "*    | Input: coins = [1,2,5], amount = 11                      *\n" +
            "*    | Output: 3                                                *\n" +
            "*    | Explanation: 11 = 5 + 5 + 1                              *\n" +
            "*                                                               *\n" +
            "* Example 2:                                                    *\n" +
            "*    | Input: coins = [2], amount = 3                           *\n" +
            "*    | Output: -1                                               *\n" +
            "*                                                               *\n" +
            "*  Example 3:                                                   *\n" +
            "*    | Input: coins = [1], amount = 0                           *\n" +
            "*    | Output: 0                                                *\n" +
            "*                                                               *\n" +
            "* Constraints:                                                  *\n" +
            "*    | 1 <= coins.length <= 12                                  *\n" +
            "*    | 1 <= coins[i] <= 231 - 1                                 *\n" +
            "*    | 0 <= amount <= 10^4                                      *\n" +
            "*****************************************************************\n"

                println(codeChallenge)
    }

    fun coinChange(coins: IntArray, amount: Int): Int {
        printSolutionStart(coins, amount)

        // Initializing the cache array used by the Dynamic Programming, with all values being "amount + 1" (non-valid solution)
        val dp = IntArray(amount + 1) { amount + 1}
        // and will update the first element of this array to zero
        dp[0] = 0

        // Iterate from 1 until the amount, to build up our cache with the minium coins for each amount
        for (amountRunner in 1..amount) {
            for (coin in coins) {
                // When subtracting the current amount by the current coin and it provides a valid response, a
                // positive or zero leftover, then we 'may' use this coin...
                if (amountRunner - coin >= 0) {
                    // The solution for this sub-problem will be the minimum value between the cache value for this
                    // current amount OR value of "amountRunner - coin" + 1 (1 == this current coin)
                    dp[amountRunner] = minOf(
                        dp[amountRunner],
                        dp[amountRunner - coin] + 1,
                    )
                }
            }
        }

        // If the value stored for 'amount' in the cache is "amount + 1" (the same value initially store), then
        // it means that no solution was found for this problem.
        return if(dp[amount] == amount + 1) {
            (-1).printSolutionEnd()
        } else {
            dp[amount].printSolutionEnd()
        }
    }
}

private fun printSolutionStart(coins: IntArray, amount: Int) {
    println("=================================================================")
    println("- Input: coins = ${coins.printableIntArray()}, amount: $amount")
}

private fun Int.printSolutionEnd(): Int {
    println("- Output: $this")
    println("=================================================================\n")
    return this
}