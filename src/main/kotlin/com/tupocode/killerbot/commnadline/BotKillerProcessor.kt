package com.tupocode.killerbot.commnadline

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class BotKillerProcessor(val telegramService: TelegramService, val properties: TelegramBotConfiguration) : CommandLineRunner {
    override fun run(vararg args: String?) {
        var running = true

        println(properties.baseUrl)

//        while(running) {
//            val updates = telegramService.getUpdates()
//            println("Bla")
//        }

    }
}