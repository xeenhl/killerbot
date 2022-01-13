package com.tupocode.killerbot.commnadline

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.ChatMember
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class BotKillerProcessor(val telegramService: TelegramService, val properties: TelegramBotConfiguration) : CommandLineRunner {
    override fun run(vararg args: String?) {
        var running = true

        while(running) {

            val updates = telegramService.getUpdates()

            if(updates.ok) {
                updates.result.map { it.message }
                    .filterNotNull()
                    .filter { it.newChatMember != null }
                        //TODO identify killer bot itself with response not hardcoded id
                    .forEach { if(it.newChatMember?.isBot == true && it.newChatMember.id != 5069886195) telegramService.banUser(it.chat.id, it.newChatMember.id, true) else println("user ${it.newChatMember} is not a bot") }
            }

            Thread.sleep(5_000)

        }

    }
}