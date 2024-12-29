package aoc2024

import Common.GetScanner
import kotlin.math.abs

fun main() {
    val records: MutableList<List<Int>> = mutableListOf()

    GetScanner.getStream(2024,"Day-2").bufferedReader().useLines { lines -> lines.forEach { records.add(it.split(" ").map { it.toInt() }) } }

    var count = 0
    records.forEach { if(checkSafe(it,false)){count++} }
    println(count)
    count = 0
    records.forEach { if(checkSafe(it,true)){count++} }
    println(count)

}

fun checkSafe(record: List<Int>, part2: Boolean): Boolean {
    var safe = true
    var lastInt = record.first()
    var i = 1
    var growing = false
    var failedI = -1

    if (record.size < 2) return true

    if( record[1] > lastInt){ growing = true}
    while(safe && i < record.size ){

        val abs = abs(record[i] - lastInt)
        if(abs < 1 ||  3 < abs){safe = false }

        if( record[i] <= lastInt && growing ){ safe = false }
        else if(record[i] >= lastInt && !growing ){safe = false }

        if(!safe){failedI = i}
        lastInt = record[i]
        i++

    }

    var newSafe = false
    var x = 0
    if( (!safe)&& part2){
        while (!newSafe && x < record.size){
            newSafe = checkSafe(record.filterIndexed { i, _ -> i != x }, false)
            x++
        }
    }
    if (newSafe){return true}

    return safe
}