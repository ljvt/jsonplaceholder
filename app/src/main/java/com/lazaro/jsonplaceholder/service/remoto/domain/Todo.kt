package com.lazaro.jsonplaceholder.service.remoto.domain

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)