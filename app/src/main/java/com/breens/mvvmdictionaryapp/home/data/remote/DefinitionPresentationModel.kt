package com.breens.mvvmdictionaryapp.home.data.remote


data class DefinitionPresentationModelItem(
    val meanings: List<MeaningPresentationModel>? = null,
    val origin: String? = null,
    val phonetic: String? = null,
    val phonetics: List<PhoneticPresentationModel>? = null,
    val word: String? = null
)

data class MeaningPresentationModel(
    val definitions: List<DefinitionPresentationModel>? = null,
    val partOfSpeech: String? = null
)

data class DefinitionPresentationModel(
    val antonyms: List<Any>? = null,
    val definition: String? = null,
    val example: String? = null,
    val synonyms: List<Any>? = null
)

data class PhoneticPresentationModel(
    val audio: String? = null,
    val text: String? = null
)