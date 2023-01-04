package com.breens.mvvmdictionaryapp.model

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)