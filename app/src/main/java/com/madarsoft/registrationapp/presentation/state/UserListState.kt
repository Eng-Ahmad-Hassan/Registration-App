package com.madarsoft.registrationapp.presentation.state

import com.madarsoft.registrationapp.domain.model.User
import androidx.paging.PagingData
 
sealed class UserListState {
    object Loading : UserListState()
    data class Success(val users: PagingData<User>) : UserListState()
    data class Error(val message: String) : UserListState()
} 