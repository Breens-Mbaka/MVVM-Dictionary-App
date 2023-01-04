package com.breens.mvvmdictionaryapp.home.data.remote.model

data class DefinitionResponseItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)