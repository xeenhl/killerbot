package com.tupocode.killerbot.telegram

import com.fasterxml.jackson.databind.ObjectMapper
import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.*
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.*
import java.time.Instant

@Service
class TelegramServiceImpl(configuration: TelegramBotConfiguration, val objectMapper: ObjectMapper) : TelegramService {

    val client = HttpClient.newBuilder().build();
    var offset = 0L
    final val baseUrl = "${configuration.baseUrl}${configuration.token}"
    val banUserUrl = "$baseUrl${configuration.banUser}"
    val sendTextUrl = "$baseUrl${configuration.sendText}"
    val getUpdateUrl = "$baseUrl${configuration.getUpdate}"
    val promoteUserUrl = "$baseUrl${configuration.promoteUser}"

    override fun getUpdates(): UpdateResponse {
        val request = HttpRequest.newBuilder().GET().uri(
            URIBuilder(getUpdateUrl)
                .param("offset", offset)
                .build()
        ).build()
        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, UpdateResponse::class.java)

        offset = if(response.result.size > 0) response.result.maxOf { it.updateId }.inc() else offset

        return response
    }

    override fun banUser(chatId: Long, userId: Long, revoke: Boolean) : BanBotResponse {
        val request = HttpRequest.newBuilder().GET().uri(
            URIBuilder(banUserUrl)
                .param("chat_id", chatId)
                .param("user_id", userId)
                .param("until_date", Instant.now().plusSeconds(90).toEpochMilli())
                .param("revoke_messages", revoke)
                .build())
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, BanBotResponse::class.java)
        return response
    }

    override fun sendText(chatId: Long, text: String) : SendTextResponse {
        val encoded = URLEncoder.encode(text, "UTF-8")
        val request = HttpRequest.newBuilder().GET().uri(
            URIBuilder(sendTextUrl)
                .param("chat_id", chatId)
                .param("text", encoded)
                .build())
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, SendTextResponse::class.java)
        return response
    }

    override fun replyToMessage(chatId: Long, replyToId: Long, text: String): SendTextResponse {
        val encoded = URLEncoder.encode(text, "UTF-8")
        val request = HttpRequest.newBuilder().GET().uri(
            URIBuilder(sendTextUrl)
                .param("chat_id", chatId)
                .param("text", encoded)
                .param("reply_to_message_id", replyToId)
                .build())
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, SendTextResponse::class.java)
        return response
    }

    override fun grantPermissionsToUser(chatId: Long, userId: Long, rights: ChatPermissions): GrantPermissionsResponse {
        val request = HttpRequest.newBuilder().GET().uri(
            URIBuilder(promoteUserUrl)
                .param("chat_id", chatId)
                .param("user_id", userId)
                .param("can_manage_chat", rights.can_manage_chat)
                .param("can_post_messages", rights.can_post_messages)
                .param("can_edit_messages", rights.can_edit_messages)
                .param("can_delete_messages", rights.can_delete_messages)
                .param("can_manage_voice_chats", rights.can_manage_voice_chats)
                .param("can_restrict_members", rights.can_restrict_members)
                .param("can_promote_members", rights.can_promote_members)
                .param("can_change_info", rights.can_change_info)
                .param("can_invite_users", rights.can_invite_users)
                .param("can_edit_messages", rights.can_edit_messages)
                .param("can_pin_messages", rights.can_pin_messages)
                .build())
            .build()

        val res = client.send(request, BodyHandlers.ofString()).body()
        val response = objectMapper.readValue(res, GrantPermissionsResponse::class.java)
        return response
    }

    class URIBuilder internal constructor (url: String) {

        private val builder = StringBuilder(url)
        private var firstParam = true
        fun param(paramKey: String, paramVal: Any) : URIBuilder {
            if(firstParam) {
                builder.append("?$paramKey=$paramVal")
                firstParam = false
            } else {
                builder.append("&$paramKey=$paramVal")
            }

            return this
        }
        fun build() : URI = URI.create(builder.toString())
    }
}