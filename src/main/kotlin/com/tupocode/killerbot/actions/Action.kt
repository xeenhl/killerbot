package com.tupocode.killerbot.actions

import com.tupocode.killerbot.model.UpdateResponse

interface Action<T> {
    fun label() : Label
    fun apply(data: T) : Boolean
}

enum class Label {
    DELETE_BOT, ASK_ADMIN, VERIFY_QUIZ
}
