package atmwithdraw

import utils.assertWith

fun main() {

    val atmWithdraw = AtmWithdraw()

    atmWithdraw.solution(1,5,4,4,1700).assertWith("500-3,200-1")
    atmWithdraw.solution(1,1,4,0,600).assertWith("200-3")
    atmWithdraw.solution(1,1,4,4,400).assertWith("200-2")
}