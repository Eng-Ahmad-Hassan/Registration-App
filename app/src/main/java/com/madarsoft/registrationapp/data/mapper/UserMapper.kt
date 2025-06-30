package com.madarsoft.registrationapp.data.mapper

import com.madarsoft.registrationapp.data.local.entity.UserEntity
import com.madarsoft.registrationapp.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
} 