package com.example.sipapp.data

import android.util.Log
import com.example.sipapp.data.local.UserEntity
import com.example.sipapp.data.mappers.toEntity
import com.example.sipapp.data.mappers.toModel
import com.example.sipapp.data.remote.ApiService
import com.example.sipapp.domain.UserModel
import com.example.sipapp.domain.UsersRepository
import io.realm.kotlin.Realm
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val realm: Realm,
    private val apiService: ApiService
): UsersRepository {
    override suspend fun getUsers(): List<UserModel> {
        val users = apiService.getUsers()
        val result = mutableListOf<UserModel>()
        realm.writeBlocking {
            deleteAll()
            users.forEach { dto ->
                val entity = dto.toEntity()
                copyToRealm(entity)
                result.add(entity.toModel())
            }
        }
        Log.d("test2","$result")
        return result
    }

    override suspend fun getUser(userId: Int): UserModel? {
        return realm.query(UserEntity::class, "id == $0", userId).first().find()?.toModel()
    }


}