package com.tupocode.killerbot.telegram

import com.tupocode.killerbot.model.*

interface TelegramService {
    fun getUpdates(): UpdateResponse
    fun banUser(chatId: Long, userId: Long, revoke: Boolean = true): BanBotResponse
    fun sendText(chatId: Long, text: String) : SendTextResponse
    fun replyToMessage(chatId: Long, replyToId: Long, text: String) : SendTextResponse
    fun grantPermissionsToUser(chatId: Long, userId: Long, rights: ChatPermissions): GrantPermissionsResponse
}