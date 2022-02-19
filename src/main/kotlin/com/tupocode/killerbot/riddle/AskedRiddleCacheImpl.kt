package com.tupocode.killerbot.riddle

import com.tupocode.killerbot.model.Riddle
import org.springframework.stereotype.Service

@Service
class AskedRiddleCacheImpl : AskedRiddleCache{

    private val cache: MutableMap<String, Riddle> = mutableMapOf()

    override fun cacheRiddle(chatId: Long, userId: Long, riddle: Riddle): Riddle? = cache.put("$chatId$userId", riddle)

    override fun removeRiddleForUserInChat(chatId: Long, userId: Long): Riddle? = cache.remove("$chatId$userId")

    override fun getRiddleForUserInChat(chatId: Long, userId: Long): Riddle? = cache.get("$chatId$userId")
}