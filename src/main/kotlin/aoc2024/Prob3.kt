package aoc2024

import Common.GetScanner
import java.util.regex.Pattern


fun main() {

    val text: String = GetScanner.getStream(2024,"Day-3").bufferedReader().readText()

    val pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
    val matcher = pattern.matcher(text)
    val mulList = matcher.results().map { it.group() }.toList()

    var sum = 0
    var doBool = true
    mulList.forEach {
        if(it == "do()" ){
            doBool = true
        } else if(it == "don't()" ){
            doBool = false
        } else if(doBool) {
            sum += calculate(it)
        }
    }

    println(sum)


}

fun calculate(mul:String): Int{

    var cleanedMul = mul.drop(4).dropLast(1)
    var ints: List<Int> = cleanedMul.split(",").map { it.toInt() }

    return ints[0] * ints[1]

}

