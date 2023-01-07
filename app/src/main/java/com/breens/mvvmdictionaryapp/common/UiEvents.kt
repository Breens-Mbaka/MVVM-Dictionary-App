package com.breens.mvvmdictionaryapp.common

sealed class UiEvents {
    data class SnackBarEvent(val message: String) : UiEvents()
}