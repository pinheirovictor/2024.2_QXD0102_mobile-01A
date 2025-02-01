package com.example.msgapp1.repository

import com.example.msgapp1.data.local.dao.MessageDao
import com.example.msgapp1.model.Message
import kotlinx.coroutines.flow.Flow


class MessageRepository(private val dao: MessageDao) {

    val allMessages: Flow<List<Message>> = dao.getAllMessages()

    suspend fun addMessage(content: String) {
        val message =
            Message(
                content = content,
                timestamp = System.currentTimeMillis()
            )

        dao.insertMessage(message)
    }
}