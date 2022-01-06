package com.tupocode.killerbot.telegram

import com.tupocode.killerbot.model.UpdateResponse

interface TelegramService {
    fun getUpdates() : UpdateResponse
}