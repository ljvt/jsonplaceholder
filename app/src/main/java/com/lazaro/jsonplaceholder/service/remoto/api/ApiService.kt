package com.lazaro.jsonplaceholder.service.remoto.api

import com.lazaro.jsonplaceholder.service.remoto.domain.Post
import com.lazaro.jsonplaceholder.service.remoto.domain.Todo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}