package validparentheses

import utils.assertWith

fun main() {
    val solution = ValidParentheses()

    solution.isValid("()").assertWith(true)
    solution.isValid("()[]{}").assertWith(true)
    solution.isValid("(]").assertWith(false)

}