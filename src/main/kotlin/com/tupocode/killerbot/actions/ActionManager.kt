package com.tupocode.killerbot.actions

import org.springframework.stereotype.Service

@Service
class ActionManager() {
    val actions: MutableMap<Label, Action<Any>> = mutableMapOf()
}