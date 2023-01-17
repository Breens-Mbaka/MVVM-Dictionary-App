package com.breens.mvvmdictionaryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            DictionaryApp()
        }
    }

    @Composable
    fun DictionaryApp() {
        MVVMDictionaryAppTheme {

            val navHostController = rememberNavController()

            DictionaryAppNavHost(
                navHostController = navHostController
            )
        }
    }
}