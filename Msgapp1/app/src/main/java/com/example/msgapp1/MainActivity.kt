package com.example.msgapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.msgapp1.data.local.database.AppDatabase
import com.example.msgapp1.repository.MessageRepository
import com.example.msgapp1.ui.theme.MsgApp1Theme
import com.example.msgapp1.ui.view.MessageApp
import com.example.msgapp1.viewmodel.MessageViewModel
import com.example.msgapp1.viewmodel.MessageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "messages-db"
        ).fallbackToDestructiveMigration().build()
        val repository = MessageRepository(db.messageDao())

        setContent {
            MsgApp1Theme {
                val viewModel: MessageViewModel =
                    viewModel(factory = MessageViewModelFactory(repository))
                MessageApp(viewModel)
            }
        }
    }
}
