package com.breens.mvvmdictionaryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMDictionaryAppTheme {

            }
        }
    }
}