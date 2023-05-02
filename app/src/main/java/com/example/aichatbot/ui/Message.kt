package com.example.aichatbot.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aichatbot.R
import com.example.aichatbot.data.Message
import com.example.aichatbot.utis.BotResponse
import com.example.aichatbot.utis.Constants.RECEIVE_ID
import com.example.aichatbot.utis.Constants.SEND_ID
import kotlinx.coroutines.*
import java.sql.Timestamp


class MainActivity : AppCompatActivity() {


    private lateinit var adapter: MessagingAdapter
    private lateinit var sendButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var message_me: EditText
    private lateinit var rv_messages: RecyclerView
    private lateinit var text_chat_message_me: android.widget.TextView
    private val botList = listOf("Troy", "Tina")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()

        clickEvents()

        // Expected output of the chatbot when page is opened

        val random = (0..1).random()
        customBotMessage("Hello! My name is ${botList[random]},how are you feeling?")
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Suppress("DEPRECATION")

    // Send button and back button

    private fun clickEvents() {
        sendButton = findViewById(R.id.sendButton)
        sendButton.setOnClickListener {
            sendMessage()
        }
        message_me = findViewById(R.id.text_chat_message_me)
        message_me.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
        rv_messages = findViewById(R.id.rv_messages)
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

    }

    // RecyclerView //

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages = findViewById(R.id.rv_messages)
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }


    // User sends message //

    @SuppressLint("WrongViewCast")
    private fun sendMessage() {
        val message = message_me.text.toString()
        if (message.isEmpty()) {
            return
        }

        val timeStamp = System.currentTimeMillis()
        val messageObj = Message(message, SEND_ID, Timestamp(timeStamp))
        adapter.insertMessage(messageObj)
        rv_messages.scrollToPosition(adapter.itemCount - 1)
        botResponse(message)
    }


    // Bot Response
    @OptIn(DelicateCoroutinesApi::class)
    private fun botResponse(message: String) {
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            val response = BotResponse.basicResponse(message)
            val timeStamp = System.currentTimeMillis()
            val messageObj = Message(response, RECEIVE_ID, Timestamp(timeStamp))

            withContext(Dispatchers.Main) {
                adapter.insertMessage(messageObj)
                adapter.notifyItemInserted(adapter.itemCount - 1)
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    // Bot Message //
    @OptIn(DelicateCoroutinesApi::class)
    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)

            val timeStamp = System.currentTimeMillis()
            adapter.insertMessage(Message(message, RECEIVE_ID, Timestamp(timeStamp)))
            withContext(Dispatchers.Main) {
                adapter.notifyItemInserted(adapter.itemCount - 1)
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}
