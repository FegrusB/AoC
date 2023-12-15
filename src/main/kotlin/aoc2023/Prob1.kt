package aoc2023

import Common.GetScanner

fun main(){

    val stream = GetScanner.getStream(2023,"Day-1").bufferedReader().readLines()
    val strings =  stream.toMutableList()
    val numberStrings = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val numbers = listOf(1,2,3,4,5,6,7,8,9)

    val calibrationTotal = strings.sumOf { getCalibrationValue(it)}
    println("Initial total:  $calibrationTotal")

    val newStrings = mutableListOf<String>()
    strings.forEach { str ->
        var tempStr = str
        numbers.forEach {tempStr = tempStr.replace(numberStrings[it-1],numberStrings[it-1].first() + it.toString() + numberStrings[it-1].last()) }
        newStrings.add(tempStr)
    }

    val part2Total = newStrings.sumOf { getCalibrationValue(it)}
    println("Part 2 total:  $part2Total")

}

fun  getCalibrationValue(str: String): Int {
    val digits = str.filter { it.isDigit() }
    val first = digits.first()
    val last = digits.last()
    return ("$first$last").toInt()
}