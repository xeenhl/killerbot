package com.tupocode.killerbot.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class TelegramBotConfiguration(
    @Value("{telegram.bot.token}")
    var token: String? = null,

    @Value("{telegram.bot.base.url}")
    var baseUrl: String? = null,

    @Value("{telegram.bot.url.token.template}")
    var tokenUrlTemplate: String? = null,

    @Value("{telegram.bot.url.get.updates}")
    var getUpdate: String? = null,

    @Value("{telegram.bot.url.get.me}")
    var getMe: String? = null,

    @Value("{telegram.bot.timeout}")
    var timeout: Int? = null
)