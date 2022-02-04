package com.tupocode.killerbot.riddle

import com.tupocode.killerbot.model.Riddle

interface AskedRiddleCache {
    fun cacheRiddle(chatId: Long, userId: Long, riddle: Riddle): Riddle?
    fun removeRiddleForUserInChat(chatId: Long, userId: Long) : Riddle?
    fun getRiddleForUserInChat(chatId: Long, userId: Long) : Riddle?
}