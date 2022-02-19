package com.tupocode.killerbot.processor

import com.tupocode.killerbot.TelegramBotConfiguration
import com.tupocode.killerbot.actions.ActionManager
import com.tupocode.killerbot.actions.Label
import com.tupocode.killerbot.model.Message
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class MessageProcessor(val actionManager: ActionManager, val botConfig: TelegramBotConfiguration) {
    fun process(msg: Message) = runBlocking {
        launch {

            when(true) {
                isNewChatMember(msg) -> actionManager.actions[Label.DELETE_BOT]?.apply(msg)
//                isAdminRequest(msg) -> actionManager.actions[Label.ASK_ADMIN]?.apply(msg)
                isReplayToBot(msg, botConfig.botid) -> actionManager.actions[Label.VERIFY_QUIZ]?.apply(msg)
            }

        }

    }
}

fun isAdminRequest(msg: Message) = msg.text?.startsWith("/getAdmin")
fun isReplayToBot(msg: Message, botId: Long) = msg.replayToMessage != null && msg.replayToMessage.from.id == botId
fun isNewChatMember(msg: Message) = msg.newChatMember != null