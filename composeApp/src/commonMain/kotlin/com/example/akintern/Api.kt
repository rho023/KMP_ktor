package com.example.akintern

import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Api {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Ignore fields in the response that are not in the data class
                isLenient = true // Allow lenient parsing
            })
        }
    }

    suspend fun getBreaches(): List<Breach> {
        return client.get("https://haveibeenpwned.com/api/v2/breaches").body()
    }
}

@Serializable
data class Breach(
    val Title: String,
    val Domain: String,
    val BreachDate: String,
    val Description: String
)