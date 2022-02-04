package com.tupocode.killerbot.riddle

import com.tupocode.killerbot.model.Riddle

interface RiddleService {
    fun getNextRiddle() : Riddle
    fun validateRiddleAnswer(riddleId: Long, answer: String) : Boolean
}