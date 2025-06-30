package com.madarsoft.registrationapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madarsoft.registrationapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingSource

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun pagingSourceAllUsers(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%' OR jobTitle LIKE '%' || :query || '%' ORDER BY id DESC")
    fun pagingSourceSearchUsers(query: String): PagingSource<Int, UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)
} 