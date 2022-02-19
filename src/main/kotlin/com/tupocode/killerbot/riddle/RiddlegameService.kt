package com.tupocode.killerbot.riddle

import com.tupocode.killerbot.model.Riddle

interface RiddleGameService {
    fun getNextRiddle() : Riddle
    fun validateRiddleAnswer(riddleId: Long, answer: String) : Boolean
}