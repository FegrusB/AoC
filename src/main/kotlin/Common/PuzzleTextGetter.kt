package Common

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cookies.ConstantCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.Cookie

suspend fun getPuzzleText(): HttpResponse {
    val client = HttpClient(CIO) {
        install(HttpCookies) {
            storage = ConstantCookiesStorage(Cookie(name = "session", value = GetScanner.getCookie(), domain = "adventofcode.com"))
        }
    }
    val response: HttpResponse = client.get("https://adventofcode.com/2024/day/1/input")
    return response
}

fun main() = kotlinx.coroutines.runBlocking {
    println(getPuzzleText().bodyAsText())
}

