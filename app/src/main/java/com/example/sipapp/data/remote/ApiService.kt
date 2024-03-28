package com.example.sipapp.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET ("users")
    suspend fun getUsers(): List<UserDto>
}