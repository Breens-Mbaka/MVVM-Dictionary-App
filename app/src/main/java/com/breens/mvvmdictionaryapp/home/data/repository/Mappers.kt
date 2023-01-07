package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.home.data.remote.Definition
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModel
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionResponseModel
import com.breens.mvvmdictionaryapp.home.data.remote.Meaning
import com.breens.mvvmdictionaryapp.home.data.remote.MeaningPresentationModel
import com.breens.mvvmdictionaryapp.home.data.remote.Phonetic
import com.breens.mvvmdictionaryapp.home.data.remote.PhoneticPresentationModel

internal fun DefinitionResponseModel.toPresentation() =
    DefinitionPresentationModelItem(
        meanings = meanings.map { meaning ->
            meaning.toPresentation()
        },
        phonetic = phonetic,
        phonetics = phonetics.map { phonetic ->
            phonetic.toPresentation()
        },
        word = word
    )

private fun Meaning.toPresentation() =
    MeaningPresentationModel(
        definitions = definitions.map { definition ->
            definition.toPresentation()
        },
        partOfSpeech = partOfSpeech
    )

private fun Definition.toPresentation() =
    DefinitionPresentationModel(
        antonyms = antonyms,
        definition = definition,
        synonyms = synonyms
    )

private fun Phonetic.toPresentation() =
    PhoneticPresentationModel(
        audio = audio,
        text = text
    )