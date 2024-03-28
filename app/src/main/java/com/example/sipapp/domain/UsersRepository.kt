package com.example.sipapp.domain

interface UsersRepository {
    suspend fun getUsers(): List<UserModel>
    suspend fun getUser(userId: Int): UserModel?
}
