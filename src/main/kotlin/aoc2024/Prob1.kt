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

    val map: HashMap<Int, Int> = HashMap()

    var sum = 0
    for (i in 0..<listA.size){
        sum += abs(listA[i] - listB[i])
        map.put(listA[i],listA[i] * listB.count { it == listA[i] })
    }
    println(sum)
    println(map.values.sum())
}