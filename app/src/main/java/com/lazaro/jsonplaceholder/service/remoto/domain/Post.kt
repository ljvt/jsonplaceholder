package com.lazaro.jsonplaceholder.service.remoto.domain

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)