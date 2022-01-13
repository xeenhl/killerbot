package com.tupocode.killerbot.telegram

import com.fasterxml.jackson.databind.DatabindException
import com.fasterxml.jackson.databind.ObjectMapper
import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.UpdateResponse
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.*
import java.time.Instant
import java.util.*

@Service
class TelegramServiceImpl(configuration: TelegramBotConfiguration, val objectMapper: ObjectMapper) : TelegramService {

    val client = HttpClient.newBuilder().build();
    var offset = 0L
    final val baseUrl = "${configuration.baseUrl}${configuration.token}"
    val banUserUrl = "$baseUrl${configuration.banUser}"
    val getUpdateUrl = "$baseUrl${configuration.getUpdate}"

    override fun getUpdates(): UpdateResponse {
        val request = HttpRequest.newBuilder().GET().uri(URI.create(
            "${getUpdateUrl}?offset=${offset}"))
            .build()
        val response = client.send(request, BodyHandlers.ofString()).body()
        val responses = objectMapper.readValue(response, UpdateResponse::class.java)

        offset = if(responses.result.size > 0) responses.result.maxOf { it.updateId }.inc() else offset

        return responses
    }

    override fun banUser(chatId: Long, userId: Long, revoke: Boolean) {
        val request = HttpRequest.newBuilder().GET().uri(URI.create(
            "${banUserUrl}?chat_id=${chatId}&user_id=${userId}&until_date=${Instant.now().plusSeconds(90).toEpochMilli()}&revoke_messages=${revoke}"))
            .build()

        val response = client.send(request, BodyHandlers.ofString()).body()
        println(response)
    }
}