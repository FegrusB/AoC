package aoc2024

import Common.GetScanner
import kotlin.math.abs

fun main(){
    val stream = GetScanner.getStream(2024,"Day-1").bufferedReader().readLines()
    val listA : MutableList<Int> = mutableListOf()
    val listB : MutableList<Int> = mutableListOf()
    stream.forEach {
        val split = it.split("   ")
        listA.add(split[0].toInt())
        listB.add(split[1].toInt())
    }

    listA.sort()
    listB.sort()

    val countList: MutableList<Int> = MutableList(listA.size){0}

    var sum = 0
    for (i in 0..<listA.size){
        sum += abs(listA[i] - listB[i])
        listB.forEach {
            if (it == listA[i]) countList[i]++
        }
    }
    println(sum)

    sum = 0
    for (i in 0..<countList.size){
        sum += countList[i]*listA[i]
    }

    println(sum)
}