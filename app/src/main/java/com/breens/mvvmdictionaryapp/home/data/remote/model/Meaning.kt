package com.breens.mvvmdictionaryapp.home.data.remote.model

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)