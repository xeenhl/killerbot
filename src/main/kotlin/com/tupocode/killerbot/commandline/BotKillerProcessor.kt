package com.tupocode.killerbot.commandline

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.Message
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class BotKillerProcessor(val telegramService: TelegramService, val properties: TelegramBotConfiguration) : CommandLineRunner {
    override fun run(vararg args: String?) {
        var running = true

        while(running) {

            val updates = telegramService.getUpdates()

            fun isBot(it: Message) = it.newChatMember?.isBot == true
            fun isKillerBot(it: Message) = it.newChatMember?.id == properties.botid

            if(updates.ok) {
                updates.result.map { it.message }
                    .filterNotNull()
                    .filter { it.newChatMember != null }
                    .forEach { if(isBot(it) && !isKillerBot(it)) banTheBot(it) else println("user ${it.newChatMember} is not a bot") }
            }

            Thread.sleep(5_000)

        }

    }

    private fun banTheBot(it: Message) {
        val response = telegramService.banUser(it.chat.id, it.newChatMember!!.id, true)
        if(response.ok) {
            telegramService.sendText(it.chat.id, "This chat is too small for two of us")
        }
    }
}