package com.breens.mvvmdictionaryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.breens.mvvmdictionaryapp.common.UiEvents
import com.breens.mvvmdictionaryapp.home.presentation.DefinitionViewModel
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val definitionViewModel: DefinitionViewModel = viewModel()

            DictionaryApp(definitionViewModel = definitionViewModel)
        }
    }

    @Composable
    fun DictionaryApp(definitionViewModel: DefinitionViewModel) {
        MVVMDictionaryAppTheme {

            val navHostController = rememberNavController()

            DictionaryAppNavHost(
                navHostController = navHostController,
                definitionViewModel = definitionViewModel
            )
        }
    }
}