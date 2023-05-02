package com.example.aichatbot.utis

import java.util.Locale


object BotResponse {




    fun basicResponse(_message: String): String {

        val random = (0..5).random()
        val message = _message.toLowerCase(Locale.ROOT)

        //Hello
        return when {
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hey! How are you feeling?"
                    1 -> "Sup"
                    2 -> "Hi!"
                    else -> "error"
                }
            }
            message.contains("I'm not doing well") -> {
                when (random) {
                    0 -> "Great works are performed not by strength, but by perseverance -Samuel Johnson"
                    1 -> "It's hard to beat a person who never gives up -Babe Ruth"
                    2 -> "Everything you can imagine is real -Pablo Picasso"
                    3 -> "Do one thing every day that scares you -Eleanor Roosevelt"
                    4 -> "Do what you feel in your heart to be right-for you'll be criticized anyway -Eleanor Roosevelt"
                    else -> "error"
                }
                }
            else -> "error"
        }
        }
}