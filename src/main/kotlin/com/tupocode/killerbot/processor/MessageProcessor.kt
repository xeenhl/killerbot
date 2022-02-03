package com.tupocode.killerbot.processor

import com.tupocode.killerbot.actions.ActionManager
import com.tupocode.killerbot.actions.Label
import com.tupocode.killerbot.model.Message
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class MessageProcessor(val actionManager: ActionManager) {
    fun process(msg: Message) = runBlocking {
        launch {

            when(true) {
                isNewChatMember(msg) -> actionManager.actions[Label.DELETE_BOT]?.apply(msg)
                isAdminRequest(msg) -> actionManager.actions[Label.ASK_ADMIN]?.apply(msg)
                isReplay(msg) -> actionManager.actions[Label.VERIFY_QUIZ]?.apply(msg)
            }

        }

    }
}

fun isAdminRequest(msg: Message) = msg.text?.startsWith("/getAdmin")
fun isReplay(msg:Message) = msg.replayToMessage != null
fun isNewChatMember(msg: Message) = msg.newChatMember != null