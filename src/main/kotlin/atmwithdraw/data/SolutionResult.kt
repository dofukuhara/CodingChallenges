package atmwithdraw.data

sealed class SolutionResult {
    data class CanWithdraw(val bankNotes: String) : SolutionResult()
    object CannotWithdraw : SolutionResult()
}