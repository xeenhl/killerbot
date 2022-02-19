package com.tupocode.killerbot.actions

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.Message
import com.tupocode.killerbot.telegram.RandomPhraseService
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class InitNewGameAction(val properties: TelegramBotConfiguration,
                        val telegramService: TelegramService,
                        val randomPhraseService: RandomPhraseService) : Action<Message> {

    override fun label(): Label = Label.DELETE_BOT

    override fun apply(message: Message): Boolean {



        fun banBot(chatId: Long, botId: Long): Boolean {
            if((message.newChatMember?.isBot == true) && (message.newChatMember.id != properties.botid)) {
                val response = telegramService.banUser(chatId, botId, true)
                if(response.ok) return telegramService.sendText(chatId, randomPhraseService.getRandomPhrase()).ok
            }
            return false
        }

        return  banBot(message.chat.id, message.newChatMember?.id ?: 0L)

    }

}