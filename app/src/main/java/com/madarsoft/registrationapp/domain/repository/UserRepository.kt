package com.madarsoft.registrationapp.domain.repository

import com.madarsoft.registrationapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface UserRepository {
    suspend fun saveUser(user: User): Long
    fun getAllUsers(): Flow<List<User>>
    fun getPagedUsers(query: String? = null): Flow<PagingData<User>>
    suspend fun deleteUser(user: User)
} 