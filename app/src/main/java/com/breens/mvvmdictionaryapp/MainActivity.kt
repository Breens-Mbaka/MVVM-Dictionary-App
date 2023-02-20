package com.breens.mvvmdictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MVVMDictionaryAppTheme {
                Box(Modifier.fillMaxSize()) {
                    Text(text = "Hello World")
                }
            }
        }
    }
}