package com.madarsoft.registrationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.madarsoft.registrationapp.presentation.screen.UserFormScreen
import com.madarsoft.registrationapp.presentation.screen.UserListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserForm.route
    ) {
        composable(route = Screen.UserForm.route) {
            UserFormScreen(
                onNavigateToList = {
                    navController.navigate(Screen.UserList.route)
                }
            )
        }
        
        composable(route = Screen.UserList.route) {
            UserListScreen(
                onNavigateToForm = {
                    navController.navigate(Screen.UserForm.route) {
                        popUpTo(Screen.UserForm.route) { inclusive = true }
                    }
                }
            )
        }
    }
} 