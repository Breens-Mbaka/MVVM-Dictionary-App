package com.breens.mvvmdictionaryapp.home.data.remote


data class DefinitionPresentationModelItem(
    val meanings: List<MeaningPresentationModel>,
    val origin: String? = null,
    val phonetic: String?,
    val phonetics: List<PhoneticPresentationModel>,
    val word: String
)

data class MeaningPresentationModel(
    val definitions: List<DefinitionPresentationModel>,
    val partOfSpeech: String
)

data class DefinitionPresentationModel(
    val antonyms: List<Any>,
    val definition: String,
    val example: String? = null,
    val synonyms: List<Any>
)

data class PhoneticPresentationModel(
    val audio: String,
    val text: String
)