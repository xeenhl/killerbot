package com.tupocode.killerbot.actions

import com.tupocode.killerbot.model.ChatPermissions
import com.tupocode.killerbot.model.Message
import com.tupocode.killerbot.riddle.AskedRiddleCache
import com.tupocode.killerbot.riddle.RiddleService
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.stereotype.Component

@Component
class VerifyAdminQuizResponseAction(val riddleService: RiddleService,
                                    val askedRiddleCache: AskedRiddleCache,
                                    val telegramService: TelegramService) : Action<Message> {
    override fun label(): Label = Label.VERIFY_QUIZ

    override fun apply(data: Message): Boolean {
        val riddle = askedRiddleCache.getRiddleForUserInChat(data.chat.id, data.from.id)
        riddle?.let {
            if(riddleService.validateRiddleAnswer(it.id, data.text ?: "")) {
                telegramService.grantPermissionsToUser(data.chat.id, data.from.id, ChatPermissions())
                askedRiddleCache.removeRiddleForUserInChat(data.chat.id, data.from.id)
                telegramService.replyToMessage(data.chat.id, data.messageId, "Ну лови")
            } else {
                telegramService.replyToMessage(data.chat.id, data.messageId, "А вот Хуй!")
            }
        }
        // log admin rights and time for it
        return true
    }
}