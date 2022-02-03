package com.tupocode.killerbot.actions

import com.tupocode.killerbot.model.Message
import org.springframework.stereotype.Service

@Service
class GiveAdminRequestAction : Action<Message> {

    override fun label(): Label = Label.ASK_ADMIN

    override fun apply(data: Message): Boolean {
        // log chatId, userid, and quizId for future check
        // send quiz to chat
        TODO("Not yet implemented")
    }

}