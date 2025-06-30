package com.madarsoft.registrationapp.data.repository

import com.madarsoft.registrationapp.data.local.dao.UserDao
import com.madarsoft.registrationapp.data.mapper.toUser
import com.madarsoft.registrationapp.data.mapper.toUserEntity
import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun saveUser(user: User): Long {
        return userDao.insertUser(user.toUserEntity())
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toUser() }
        }
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toUserEntity())
    }

    override fun getPagedUsers(query: String?): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                if (query.isNullOrBlank()) userDao.pagingSourceAllUsers()
                else userDao.pagingSourceSearchUsers(query)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toUser() }
        }
    }
} 