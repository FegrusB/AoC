package org.example

fun main(){

    val stream = GetScanner.getStream("Day-11").bufferedReader().readLines()
    val monkeys: ArrayList<Monkey> = arrayListOf()

    for( i in 1..stream.size step 7){
        monkeys.add(
                Monkey(
                        stream[i].substring(18).split(", ").map { it.toInt() }
                        ,getOperation(stream[i+1].substring(19))
                        ,stream[i+2].split(" ")[5].toInt()
                        ,stream[i+3].split(" ")[9].toInt()
                        ,stream[i+4].split(" ")[9].toInt()
                ))
    }

    for(i in 1..20){
        println("Round $i")
        for (x in monkeys){x.inspect(monkeys)}
        for (x in monkeys){println("${x.items}     ${x.inspected}")}
        println()
        println()
    }

    var first = 0
    var second = 0

    for(x in monkeys){
        when{
            x.inspected > first -> {
                second = first
                first = x.inspected
            }
            x.inspected > second -> second = x.inspected
        }
    }
    val monkeyBusiness = first * second
    println("The top two monkeys inspected $first and $second items for a monkey business of $monkeyBusiness")

}

fun getOperation(operation: String): (Int) -> Int {

    val operationSplit = operation.split(" ")
    val numeric: Int? = operationSplit[2].toIntOrNull()

    if (operationSplit[1] == "*") {
        if (numeric != null) return fun(i): Int { return i * numeric } else return fun(i): Int { return i * i }
    }
    if (numeric != null) return fun(i): Int { return i + numeric } else return fun(i): Int { return i + i }
}

class Monkey(startingItems: List<Int>,
             private val operation: (Int) -> Int,
             private var divisor: Int,
             private var trueTarget: Int,
             private var falseTarget: Int,
             var inspected: Int = 0

) {

    var items: ArrayList<Int> = ArrayList(startingItems)


    fun inspect(monkeys: ArrayList<Monkey>){

        val iterator = items.iterator()

        while (iterator.hasNext()){
            inspected++
            var x = iterator.next()
            x = operation(x)
            x /= 3
            if (x % divisor == 0)  monkeys[trueTarget].catch(x) else monkeys[falseTarget].catch(x)
            iterator.remove()
        }
    }
    private fun catch(item: Int){
        items.add(item)
    }
    override fun toString(): String {return "$items      $inspected"}
}
