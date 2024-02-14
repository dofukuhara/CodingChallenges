package addbinary

import utils.assertWith

fun main() {
    val solution = AddBinary()

    solution.addBinary("11", "1").assertWith("100")
    solution.addBinary("1010", "1011").assertWith("10101")

}