package com.tupocode.killerbot

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@ConfigurationProperties("telegram")
data class TelegramBotConfiguration(
	var botid: Long = -1,
	var token: String = "",
	var baseUrl: String = "",
	var tokenUrlTemplate: String = "",
	var getUpdate: String = "",
	var banUser: String = "",
	var sendText: String = "",
	var getMe: String = "",
	var timeout: Int = 0
)

@SpringBootApplication
@EnableConfigurationProperties(TelegramBotConfiguration::class)
class KillerbotApplication {

	@Bean
	fun JaksonObjectMapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		return objectMapper
	}

}

fun main(args: Array<String>) {
	runApplication<KillerbotApplication>(*args)
}