package com.tupocode.killerbot.telegram

import com.fasterxml.jackson.databind.ObjectMapper
import com.tupocode.killerbot.TelegramBotConfiguration
import org.springframework.stereotype.Service

@Service
class RandomPhraseServiceImpl(configuration: TelegramBotConfiguration, val objectMapper: ObjectMapper) : RandomPhraseService {

    val phrases = listOf(
        "This chat is too small for two of us",
        "Good attempt, but goodbye",
        "Looks like you're learning only from experience",
        "Have a nice day ^^",
        "Only bots approved by me could stay in this chat. Just kidding")

    override fun getRandomPhrase() : String = phrases.random()
   }