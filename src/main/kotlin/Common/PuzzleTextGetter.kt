package Common

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cookies.ConstantCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.Cookie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

suspend fun getPuzzleText(year: Int, day: Int = 30): String {
    val client = HttpClient(CIO) {
        install(HttpCookies) {
            storage = ConstantCookiesStorage(Cookie(name = "session", value = GetScanner.getCookie(), domain = "adventofcode.com"))
        }
    }
    val test = "https://adventofcode.com/$year/day/$day/input"
    println(test)
    val response: HttpResponse = client.get("https://adventofcode.com/$year/day/$day/input")
    return response.bodyAsText()
}

fun main( ) = kotlinx.coroutines.runBlocking {
    val year = 2024
    val day = 3

    val f = File("src/main/resources/PuzzleText/$year/Day-$day.txt")
    withContext(Dispatchers.IO) {
        f.createNewFile()  // This is the answer to the question
        f.printWriter().use { out ->
            out.println(getPuzzleText(year,day))
        }
    }
}

