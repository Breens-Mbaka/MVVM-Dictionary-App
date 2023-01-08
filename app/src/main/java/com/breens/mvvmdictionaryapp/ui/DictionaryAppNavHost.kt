package com.breens.mvvmdictionaryapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.breens.mvvmdictionaryapp.common.Routes.DEFINITION_SCREEN
import com.breens.mvvmdictionaryapp.common.Routes.HOME_SCREEN
import com.breens.mvvmdictionaryapp.definition.DefinitionScreen
import com.breens.mvvmdictionaryapp.home.presentation.DefinitionViewModel
import com.breens.mvvmdictionaryapp.home.presentation.HomeScreen

@Composable
fun DictionaryAppNavHost(
    navHostController: NavHostController,
    definitionViewModel: DefinitionViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_SCREEN
    ) {
        composable(route = HOME_SCREEN) {
            HomeScreen(
                definitionViewModel = definitionViewModel,
                navigateToDetailScreen = {
                    navHostController.navigate(DEFINITION_SCREEN)
                }
            )
        }
        composable(route = DEFINITION_SCREEN) {
            DefinitionScreen()
        }
    }
}