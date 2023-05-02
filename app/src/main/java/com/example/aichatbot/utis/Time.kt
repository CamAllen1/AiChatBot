package com.example.aichatbot.utis

import java.text.SimpleDateFormat



object Time {
    fun getTimeStamp(): String {
        val timeStamp = System.currentTimeMillis()
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(timeStamp)
    }
}