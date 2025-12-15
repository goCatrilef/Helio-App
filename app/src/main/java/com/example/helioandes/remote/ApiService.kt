package com.example.helioandes.remote

import com.example.helioandes.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}


