package com.madarsoft.registrationapp.navigation
 
sealed class Screen(val route: String) {
    object UserForm : Screen("user_form")
    object UserList : Screen("user_list")
} 