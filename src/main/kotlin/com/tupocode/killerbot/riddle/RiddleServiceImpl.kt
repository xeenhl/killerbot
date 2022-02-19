package com.tupocode.killerbot.riddle

import com.tupocode.killerbot.model.Riddle
import org.springframework.stereotype.Service

@Service
class RiddleServiceImpl : RiddlegameService {

    override fun getNextRiddle(): Riddle {
        return Riddle(10, "Зимой и летом одним цветом", "Джира")
    }

    override fun validateRiddleAnswer(riddleId: Long, answer: String): Boolean {
        return answer == "Джира"
    }
}