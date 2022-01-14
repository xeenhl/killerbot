package com.tupocode.killerbot.telegram

import com.fasterxml.jackson.databind.DatabindException
import com.fasterxml.jackson.databind.ObjectMapper
import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.BanBotResponse
import com.tupocode.killerbot.model.SendTextResponse
import com.tupocode.killerbot.model.UpdateResponse
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URLEncoder
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
    val sendTextUrl = "$baseUrl${configuration.sendText}"
    val getUpdateUrl = "$baseUrl${configuration.getUpdate}"

    override fun getUpdates(): UpdateResponse {
        val request = HttpRequest.newBuilder().GET().uri(URI.create(
            "${getUpdateUrl}?offset=${offset}"))
            .build()
        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, UpdateResponse::class.java)

        offset = if(response.result.size > 0) response.result.maxOf { it.updateId }.inc() else offset

        return response
    }

    override fun banUser(chatId: Long, userId: Long, revoke: Boolean) : BanBotResponse {
        val request = HttpRequest.newBuilder().GET().uri(URI.create(
            "${banUserUrl}?chat_id=$chatId&user_id=$userId&until_date=${Instant.now().plusSeconds(90).toEpochMilli()}&revoke_messages=$revoke"))
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, BanBotResponse::class.java)
        return response
    }

    override fun sendText(chatId: Long, text: String) : SendTextResponse {
        val encoded = URLEncoder.encode(text, "UTF-8")
        val request = HttpRequest.newBuilder().GET().uri(URI.create(
            "${sendTextUrl}?chat_id=$chatId&text=$encoded"))
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, SendTextResponse::class.java)
        return response
    }
}