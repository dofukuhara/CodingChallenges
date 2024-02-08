package atmwithdraw

import atmwithdraw.data.BankNote
import atmwithdraw.data.SolutionResult

class AtmWithdraw {

    init {
        val codingChallenge =
            "\n" +
            "************ ATM Currency Dispenser ************\n" +
            "* This ATM has a limited amount of bank notes  *\n" +
            "* ($100, $200, $500 and $2000 bills). The user *\n" +
            "* will request an amount of money and if this  *\n" +
            "* ATM has the amount of money for the withdraw,*\n" +
            "* print out the bank note value and its count. *\n" +
            "************************************************\n"

        println(codingChallenge)
    }

    fun solution(
        twoThousands: Int, fiveHundreds: Int, twoHundreds: Int, oneHundreds: Int, input: Int
    ): String {
        val availableBanknotes = listOf(
            BankNote(2000, twoThousands),
            BankNote(500, fiveHundreds),
            BankNote(200, twoHundreds),
            BankNote(100, oneHundreds),
        )

        println("================================================")
        println("- Input:")
        println("   - Banknotes:")
        println("      - 2000: $twoThousands")
        println("      - 500: $fiveHundreds")
        println("      - 200: $twoHundreds")
        println("      - 100: $oneHundreds")
        println("   - Requested Amount:")
        println("      - $$input")
        println("- Output:")

        val result = when (val result = checkSolution(0, availableBanknotes, "", input)) {
            is SolutionResult.CanWithdraw -> {
                result.bankNotes
            }
            else -> {
                "ATM does not have enough money to process the withdraw"
            }
        }
        println(result)
        println("================================================\n")
        return result
    }

    private fun checkSolution(
        index: Int, availableBanknotes: List<BankNote>, resultMessage: String, requestedAmount: Int
    ): SolutionResult {
        if (requestedAmount % availableBanknotes[index].value == 0 &&
            requestedAmount / availableBanknotes[index].value <= availableBanknotes[index].numberAvailable
        ) {
            // End case (1 of 2) - found the necessary amount of money to withdraw
            val currentBankNoteResult = StringBuilder(resultMessage)
            if (resultMessage.isNotBlank()) {
                currentBankNoteResult.append(",")
            }
            currentBankNoteResult.append("${availableBanknotes[index].value}-${requestedAmount / availableBanknotes[index].value}")
            return SolutionResult.CanWithdraw(currentBankNoteResult.toString())
        } else if (index >= availableBanknotes.size - 1) {
            // End case (2 of 2) - reached the end of list and first check didn't completed
            return SolutionResult.CannotWithdraw
        } else {
            if (availableBanknotes[index].value > requestedAmount) {
                // If the Banknote value is greater than the requested amount, there is no way to use
                // it, so we can skip to a lower banknote value
                return checkSolution(index + 1, availableBanknotes, resultMessage, requestedAmount)
            } else if (availableBanknotes[index].numberAvailable == 0) {
                // If the Banknote is lower than the requests amount but there are zero of it in the
                // ATM, then we should skip it and move to the next lower banknote value
                return checkSolution(index + 1, availableBanknotes, resultMessage, requestedAmount)
            } else {
                // Check how many bank notes of the given value that we can use
                val maxNumberOfBankNotes = minOf(
                    availableBanknotes[index].numberAvailable,
                    requestedAmount / availableBanknotes[index].value
                )

                /**
                 *  Check all possibilities, for instance:
                 *  - banknote value: $500 ; available banknotes: 3; requested amount: $1600
                 *
                 *  Case 1: (3) $500 bills [3 x $500 == $1500] - leftover $100  -> check with smaller banknotes if we can withdraw the remaining $100
                 *  Case 2: (2) $500 bills [2 x $500 == $1000] - leftover $600  -> check with smaller banknotes if we can withdraw the remaining $600
                 *  Case 3: (1) $500 bill  [1 x $500 == $500]  - leftover $1100 -> check with smaller banknotes if we can withdraw the remaining $1100
                 */
                for (bills in maxNumberOfBankNotes downTo 0) {
                    val leftover = requestedAmount - (availableBanknotes[index].value * bills)
                    val message = if (bills == 0) {
                        resultMessage
                    } else {
                        val currentBankNoteResult = StringBuilder(resultMessage)
                        if (resultMessage.isNotBlank()) {
                            currentBankNoteResult.append(",")
                        }
                        currentBankNoteResult.append("${availableBanknotes[index].value}-$bills")
                        currentBankNoteResult.toString()
                    }
                    // Check with smaller banknotes if we can withdraw the remaining amount of money
                    // (leftover of the current process)
                    val result = checkSolution(
                        index + 1,
                        availableBanknotes,
                        message,
                        leftover
                    )
                    if (result is SolutionResult.CanWithdraw) {
                        return result
                    }
                }
                return SolutionResult.CannotWithdraw
            }
        }
    }
}