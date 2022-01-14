package com.tupocode.killerbot.telegram

import com.tupocode.killerbot.model.BanBotResponse
import com.tupocode.killerbot.model.SendTextResponse
import com.tupocode.killerbot.model.UpdateResponse

interface TelegramService {
    fun getUpdates(): UpdateResponse
    fun banUser(chatId: Long, userId: Long, revoke: Boolean = true): BanBotResponse
    fun sendText(chatId: Long,text: String) : SendTextResponse
}