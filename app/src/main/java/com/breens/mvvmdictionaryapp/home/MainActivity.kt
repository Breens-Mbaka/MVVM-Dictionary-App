package com.breens.mvvmdictionaryapp.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.breens.mvvmdictionaryapp.home.presentation.HomeScreen
import com.breens.mvvmdictionaryapp.home.ui.theme.MVVMDictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MVVMDictionaryAppTheme {
                HomeScreen()
            }
        }
    }
}