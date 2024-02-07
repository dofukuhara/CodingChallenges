package validparentheses.data

sealed class Parentheses(val isOpen: Boolean) {
    class Standard(isOpen: Boolean) : Parentheses(isOpen)
    class Curly(isOpen: Boolean) : Parentheses(isOpen)
    class Bracket(isOpen: Boolean) : Parentheses(isOpen)
    object Unknown : Parentheses(false)

    fun isAnClosingMatchOf(target: Parentheses): Boolean {
        return when(this) {
            is Standard -> this.isOpen.not() && target.isOpen && target is Standard
            is Curly -> this.isOpen.not() && target.isOpen && target is Curly
            is Bracket -> this.isOpen.not() && target.isOpen && target is Bracket
            else -> false
        }
    }
}

fun Char.getParenthesesType(): Parentheses {
    return when(this) {
        '(' -> Parentheses.Standard(true)
        ')' -> Parentheses.Standard(false)
        '{' -> Parentheses.Curly(true)
        '}' -> Parentheses.Curly(false)
        '[' -> Parentheses.Bracket(true)
        ']' -> Parentheses.Bracket(false)
        else -> Parentheses.Unknown
    }
}
