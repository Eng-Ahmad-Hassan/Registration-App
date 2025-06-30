package com.madarsoft.registrationapp.domain.usecase

import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import javax.inject.Inject
import javax.inject.Singleton

class GetAllUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getAllUsers()
    }
}

@Singleton
class GetPagedUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(query: String? = null): Flow<PagingData<User>> {
        return repository.getPagedUsers(query)
    }
} 