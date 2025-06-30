package com.madarsoft.registrationapp.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.registrationapp.R
import com.madarsoft.registrationapp.domain.model.Gender
import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.usecase.SaveUserUseCase
import com.madarsoft.registrationapp.presentation.state.UserFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(UserFormState())
    val state: StateFlow<UserFormState> = _state.asStateFlow()

    fun onNameChange(name: String) {
        _state.update { 
            it.copy(
                name = name, 
                nameError = validateName(name)
            ) 
        }
    }

    fun onAgeChange(age: String) {
        // Only allow digits
        val filteredAge = age.filter { it.isDigit() }
        _state.update { 
            it.copy(
                age = filteredAge, 
                ageError = validateAge(filteredAge)
            ) 
        }
    }

    fun onJobTitleChange(jobTitle: String) {
        _state.update { 
            it.copy(
                jobTitle = jobTitle, 
                jobTitleError = validateJobTitle(jobTitle)
            ) 
        }
    }

    fun onGenderChange(gender: Gender) {
        _state.update { it.copy(gender = gender) }
    }

    fun saveUser(onSuccess: () -> Unit) {
        val currentState = _state.value
        
        // Validate all fields before saving
        val nameError = validateName(currentState.name)
        val ageError = validateAge(currentState.age)
        val jobTitleError = validateJobTitle(currentState.jobTitle)
        
        _state.update { 
            it.copy(
                nameError = nameError,
                ageError = ageError,
                jobTitleError = jobTitleError
            ) 
        }
        
        if (nameError != null || ageError != null || jobTitleError != null) {
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            val user = User(
                name = currentState.name.trim(),
                age = currentState.age.toInt(),
                jobTitle = currentState.jobTitle.trim(),
                gender = currentState.gender
            )

            saveUserUseCase(user).fold(
                onSuccess = {
                    _state.update { it.copy(isLoading = false) }
                    onSuccess()
                    resetForm()
                },
                onFailure = { exception ->
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            nameError = context.getString(R.string.failed_save, exception.message)
                        )
                    }
                }
            )
        }
    }

    private fun validateName(name: String): String? {
        return when {
            name.isBlank() -> context.getString(R.string.full_name_required)
            name.trim().length < 2 -> context.getString(R.string.full_name_min_length)
            name.trim().all { it.isDigit() } -> context.getString(R.string.full_name_numbers_only)
            !name.trim().any { it.isLetter() } -> context.getString(R.string.full_name_letters)
            else -> null
        }
    }

    private fun validateAge(age: String): String? {
        val ageInt = age.toIntOrNull()
        return when {
            age.isBlank() -> context.getString(R.string.age_required)
            ageInt == null -> context.getString(R.string.age_valid_number)
            ageInt < 18 -> context.getString(R.string.age_min, ageInt)
            ageInt > 60 -> context.getString(R.string.age_max, ageInt)
            else -> null
        }
    }

    private fun validateJobTitle(jobTitle: String): String? {
        return when {
            jobTitle.isBlank() -> context.getString(R.string.job_title_required)
            jobTitle.trim().length < 2 -> context.getString(R.string.job_title_min_length)
            jobTitle.trim().all { it.isDigit() } -> context.getString(R.string.job_title_numbers_only)
            !jobTitle.trim().any { it.isLetter() } -> context.getString(R.string.job_title_letters)
            else -> null
        }
    }

    fun resetForm() {
        _state.value = UserFormState()
    }
} 