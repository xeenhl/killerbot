package com.tupocode.killerbot.actions

import com.tupocode.killerbot.model.Message

class VerifyAdminQuizResponseAction : Action<Message> {
    override fun label(): Label = Label.VERIFY_QUIZ

    override fun apply(data: Message): Boolean {
        // check if there loged quiz for chatId and userId
        // validate quiz response
        // if ok grant admin rights
        // log admin rights and time for it
        TODO("Not yet implemented")
    }
}