package aoc2024

import Common.GetScanner

fun main(){
    val stream = GetScanner.getStream(2024,"Day-1").bufferedReader().readLines()
    val strings =  stream.toMutableList()
    strings.forEach { println(it)}
}