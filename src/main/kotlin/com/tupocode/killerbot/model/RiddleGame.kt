package com.tupocode.killerbot.model

import java.util.*

data class RiddleGame(
    val gamers: Set<Gamer>,
    val chatId: Long,
    val riddles: Set<Riddle>,
    val currentRiddle: Riddle,
    val ended: Boolean = false,
    val winner: Gamer)

data class Riddle(val id: Long, val question: String, val answer: String)

data class Gamer(val userId: Long, val name: String)
