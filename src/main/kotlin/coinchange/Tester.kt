package coinchange

import utils.assertWith

fun main() {
    val solution = CoinChange()

    solution.coinChange(
        coins = intArrayOf(1,2,5),
        amount = 11
    ).assertWith(3)

    solution.coinChange(
        coins = intArrayOf(2),
        amount = 3
    ).assertWith(-1)

    solution.coinChange(
        coins = intArrayOf(1),
        amount = 0
    ).assertWith(0)
}