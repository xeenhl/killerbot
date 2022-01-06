package com.tupocode.killerbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@ConfigurationProperties("telegram")
data class TelegramBotConfiguration(
	var token: String = "",
	var baseUrl: String = "",
	var tokenUrlTemplate: String = "",
	var getUpdate: String = "",
	var getMe: String = "",
	var timeout: Int = 0
)

@SpringBootApplication
@EnableConfigurationProperties(TelegramBotConfiguration::class)
class KillerbotApplication

fun main(args: Array<String>) {
	runApplication<KillerbotApplication>(*args)
}