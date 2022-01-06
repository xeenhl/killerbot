package com.tupocode.killerbot.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateResponse(@JsonProperty("id") val ok: Boolean, @JsonProperty("result") val result: List<MessageResult>)

data class MessageResult(@JsonProperty("update_id") val updateId: Int, @JsonProperty("message") val message: Message)

data class Message(@JsonProperty("id") val ok: Boolean)
