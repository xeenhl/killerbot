package com.tupocode.killerbot.model

import com.fasterxml.jackson.annotation.*

data class UpdateResponse(
    @JsonProperty("id") val ok: Boolean,
    @JsonProperty("result") val result: List<MessageResult>
)

data class SendTextResponse(
    @JsonProperty("id") val ok: Boolean,
    @JsonProperty("result") val result: MessageResult
)

data class BanBotResponse(
    @JsonProperty("id") val ok: Boolean,
    @JsonProperty("result") val result: Boolean
)

data class MessageResult(
    @JsonProperty("update_id") val updateId: Long,
    @JsonProperty("message") val message: Message? = null,
)

data class Message (
    @JsonProperty("message_id")
    val messageId: Long,
    @JsonProperty("from")
    val from: From,
    @JsonProperty("chat")
    val chat: Chat,
    @JsonProperty("date")
    val date: Int = -1,
    @JsonProperty("text")
    val text: String? = "",
    @JsonProperty("new_chat_member")
    val newChatMember: NewChatMember? = null
)

data class Chat (
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("title")
    val title: String? = "",
    @JsonProperty("type")
    val type: String = ""
)

data class From (
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("is_bot")
    val isBot: Boolean = false,
    @JsonProperty("first_name")
    val firstName: String = "",
    @JsonProperty("last_name")
    val lastName: String? = ""
)

data class NewChatMember (
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("is_bot")
    val isBot: Boolean = false,
    @JsonProperty("first_name")
    val firstName: String = "",
    @JsonProperty("last_name")
    val lastName: String? = "",
    @JsonProperty("username")
    val username: String = "",
)