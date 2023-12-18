package aoc2023

import Common.GetScanner
import kotlin.math.min

fun main(){

    val stream = GetScanner.getStream(2023,"Day-2").bufferedReader().readLines()
    val gameList = mutableListOf <MutableList<CubeSet>>()

    stream.forEach {
        val splitList = it.split(" ")
        val game = mutableListOf<CubeSet>()

        for(i in 2..<splitList.size step 2){

            var colour: Colour = Colour.INIT
            if (splitList[i+1].contains("blue")){ colour = Colour.BLUE}
            else if (splitList[i+1].contains("red")){ colour = Colour.RED }
            else if (splitList[i+1].contains("green")){colour = Colour.GREEN}

            game.add(CubeSet(splitList[i].toInt(), colour))
        }
        gameList.add(game)
    }

    println(part1(gameList))
    println(part2(gameList))
}

fun part2(gameList: MutableList<MutableList<CubeSet>>): Int{
    var sum  = 0
    gameList.forEach {
        val mins = minCubes(it)
        val power = mins[0]*mins[1]*mins[2]
        sum += power
    }
    return sum
}

fun minCubes(game: MutableList<CubeSet>): List<Int> {
    val maxRed = game.filter { it.colour == Colour.RED }.maxOfOrNull {it.num}!!
    val maxGreen = game.filter { it.colour == Colour.GREEN }.maxOfOrNull {it.num}!!
    val maxBlue = game.filter { it.colour == Colour.BLUE }.maxOfOrNull {it.num}!!
    return listOf(maxRed,maxGreen,maxBlue)
}

fun part1(gameList: MutableList<MutableList<CubeSet>>): Int{
    var sum = 0
    for (i in 0..<gameList.size){
        var possible = true
        gameList[i].forEach{if(possible){possible = checkPossible(it)}}
        if (possible){sum+= (i+1)}
    }
    return sum
}

fun checkPossible(cube:CubeSet): Boolean{

    var possible = true

    when(cube.colour){
        Colour.RED -> if (cube.num > 12){possible = false}
        Colour.GREEN -> if(cube.num > 13){possible = false}
        Colour.BLUE -> if(cube.num > 14){possible = false}
        Colour.INIT -> {}
    }

    return possible
}

class CubeSet(val num: Int,val colour: Colour){
    override fun toString(): String {
        return "$colour $num"
    }
}
enum class Colour{
    RED,
    GREEN ,
    BLUE,
    INIT
}