package com.tessmerandre.app.data

data class Message(
    val id: String? = null,
    val title: String,
    val content: String,
    val observation: String?
)