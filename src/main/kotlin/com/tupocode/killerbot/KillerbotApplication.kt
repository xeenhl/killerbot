package com.tupocode.killerbot

import com.tupocode.killerbot.configuration.TelegramBotConfiguration
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@SpringBootApplication
class KillerbotApplication

fun main(args: Array<String>) {
	runApplication<KillerbotApplication>(*args)
}