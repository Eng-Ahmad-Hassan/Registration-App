package com.madarsoft.registrationapp.presentation.state

import com.madarsoft.registrationapp.domain.model.Gender

data class UserFormState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: Gender = Gender.MALE,
    val nameError: String? = null,
    val ageError: String? = null,
    val jobTitleError: String? = null,
    val isLoading: Boolean = false
) {
    fun isNameValid(): Boolean = nameError == null && name.isNotBlank()
    fun isAgeValid(): Boolean = ageError == null && age.isNotBlank()
    fun isJobTitleValid(): Boolean = jobTitleError == null && jobTitle.isNotBlank()

    fun isValid(): Boolean {
        return isNameValid() && isAgeValid() && isJobTitleValid() && !isLoading
    }
    
    fun getAgeValidationMessage(): String? {
        if (age.isBlank()) return null
        
        val ageInt = age.toIntOrNull()
        return when {
            ageInt == null -> "Age must be a valid number"
            ageInt < 18 -> "Age $ageInt is not eligible for work (minimum 18)"
            ageInt > 60 -> "Age $ageInt is not eligible for work (maximum 60)"
            else -> null
        }
    }
} 