package com.tupocode.killerbot.model

data class ChatMember( val chatId: Int, val userId: Int, val isBot: Boolean = false)
data class ChatPermissions(
    val can_manage_chat: Boolean = false,
    val can_post_messages: Boolean = true,
    val can_edit_messages: Boolean = true,
    val can_delete_messages: Boolean = true,
    val can_manage_voice_chats: Boolean = false,
    val can_restrict_members: Boolean = false,
    val can_promote_members: Boolean = false,
    val can_change_info: Boolean = false,
    val can_invite_users: Boolean = false,
    val can_pin_messages: Boolean = true,
    )