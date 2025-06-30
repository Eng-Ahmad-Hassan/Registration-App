package com.madarsoft.registrationapp.domain.model

data class User(
    val id: Long = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)

enum class Gender {
    MALE, FEMALE
} 