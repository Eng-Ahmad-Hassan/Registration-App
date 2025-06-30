package com.madarsoft.registrationapp.domain.usecase

import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return try {
            repository.deleteUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 