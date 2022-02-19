package com.tupocode.killerbot.actions

import com.tupocode.killerbot.model.Message
import com.tupocode.killerbot.riddle.AskedRiddleCache
import com.tupocode.killerbot.riddle.RiddlegameService
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.stereotype.Component

@Component
class GiveAdminRequestAction(val riddleService: RiddlegameService,
                             val askedRiddleCache: AskedRiddleCache,
                             val telegramService: TelegramService) : Action<Message> {

    override fun label(): Label = Label.ASK_ADMIN

    override fun apply(data: Message): Boolean {

        val riddle = riddleService.getNextRiddle()
        askedRiddleCache.cacheRiddle(data.chat.id, data.from.id, riddle)
        telegramService.replyToMessage(data.chat.id, data.messageId,"Отгадай загадку: \n${riddle.question}")
        return true
    }

}