package com.tupocode.killerbot.processor

import com.tupocode.killerbot.actions.ActionManager
import com.tupocode.killerbot.model.Message
import org.springframework.stereotype.Service

@Service
class MessageProcessor(val actionManager: ActionManager) {
    fun process(msg: Message) {}
}

fun isCommand(msg: Message) = msg.text?.startsWith("/")
fun isReplay(msg:Message) = msg.replayToMessage != null