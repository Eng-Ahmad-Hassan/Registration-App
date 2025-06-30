package com.madarsoft.registrationapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.registrationapp.domain.model.User
import com.madarsoft.registrationapp.domain.usecase.DeleteUserUseCase
import com.madarsoft.registrationapp.domain.usecase.GetPagedUsersUseCase
import com.madarsoft.registrationapp.presentation.state.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(kotlinx.coroutines.FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getPagedUsersUseCase: GetPagedUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    val pagedUsers = getPagedUsersUseCase().cachedIn(viewModelScope)

    fun deleteUser(user: User) {
        viewModelScope.launch {
            deleteUserUseCase(user).fold(
                onSuccess = {
                    // Users will be automatically updated through Flow
                },
                onFailure = { _ ->
                    // Optionally handle error state for UI
                }
            )
        }
    }
} 