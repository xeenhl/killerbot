package com.tupocode.killerbot.commandline

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.model.Message
import com.tupocode.killerbot.model.UpdateResponse
import com.tupocode.killerbot.processor.MessageProcessor
import com.tupocode.killerbot.telegram.RandomPhraseService
import com.tupocode.killerbot.telegram.TelegramService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class BotKillerProcessor(val telegramService: TelegramService, val messageProcessor: MessageProcessor) : CommandLineRunner {

    override fun run(vararg args: String?) {

        var running = true

        while(running) {

            val updates = telegramService.getUpdates()
            if(updates.ok) {
                updates.result.map { it.message }.filterNotNull().forEach { messageProcessor.process(it) }
            }


//            processBotDeletion(updates)
//            processGetAdminRights(updates)



            Thread.sleep(5_000)

        }

    }

//    private fun processGetAdminRights(updates: UpdateResponse) {
//
//        if(updates.ok) {
//            updates.result.map { it.message }
//                .filterNotNull()
//                .filter { it.text?.contains("/giveAdmin") == true }
//                .forEach {  println("user ${it.from.firstName} asked fo admin rights") }
//        }
//
//    }
//
//    private fun processBotDeletion(updates: UpdateResponse) {
//
//        fun isBot(it: Message) = it.newChatMember?.isBot == true
//        fun isKillerBot(it: Message) = it.newChatMember?.id == properties.botid
//
//        if(updates.ok) {
//            updates.result.map { it.message }
//                .filterNotNull()
//                .filter { it.newChatMember != null }
//                .forEach { if(isBot(it) && !isKillerBot(it)) banTheBot(it) else println("user ${it.newChatMember} is not a bot") }
//        }
//
//    }
//
//    private fun banTheBot(it: Message) {
//
//        val response = telegramService.banUser(it.chat.id, it.newChatMember!!.id, true)
//        if(response.ok) {
//            telegramService.sendText(it.chat.id, randomPhraseService.getRandomPhrase())
//        }
//
//    }
}