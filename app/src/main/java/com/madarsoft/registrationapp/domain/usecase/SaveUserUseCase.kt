package com.madarsoft.registrationapp.domain.usecase

import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Long> {
        return try {
            val userId = repository.saveUser(user)
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 