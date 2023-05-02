package com.example.aichatbot.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.aichatbot.R
import com.example.aichatbot.data.Message
import com.example.aichatbot.utis.BotResponse
import com.example.aichatbot.utis.Constants.RECEIVE_ID
import com.example.aichatbot.utis.Constants.SEND_ID



class MessagingAdapter :RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    private val MESSAGE_ME = 10
    private val BOT_MESSAGE = 11

    private var messagesList = mutableListOf<Message>()


    // Message holder //

    @SuppressLint("CutPasteId")
    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userMessageText: TextView? = null
        var botMessageText: TextView? = null
        var userMessageLayout: LinearLayout? = null
        var botMessageLayout: LinearLayout? = null
        init {
            userMessageText = itemView.findViewById(R.id.activity_main)
            botMessageText = itemView.findViewById(R.id.BotResponse)
            userMessageLayout = itemView.findViewById(com.google.android.material.R.id.message)
            botMessageLayout = itemView.findViewById(R.id.BotResponse)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MESSAGE_ME -> {
                val view = layoutInflater.inflate(R.layout.activity_main, parent, false)
                MessageViewHolder(view)
            }

            BOT_MESSAGE -> {
               val view = layoutInflater.inflate(R.layout.activity_main, parent, false)
                MessageViewHolder(view)
            }

            else -> {
                val view = layoutInflater.inflate(R.layout.activity_main, parent, false)
                MessageViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")

    // Send and Receive Message //

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        when (currentMessage.id) {
           SEND_ID -> {
               holder.userMessageText?.text = currentMessage.message
               holder.userMessageLayout?.visibility = View.VISIBLE
               holder.botMessageLayout?.visibility = View.GONE

           }
           RECEIVE_ID -> {
               val botResponse = BotResponse.basicResponse(currentMessage.message)
               holder.botMessageText?.text = botResponse
               holder.botMessageLayout?.isVisible = true
               holder.userMessageLayout?.isVisible = false

           }
        }

    }

    // Insert Message //

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size - 1)
    }
}
