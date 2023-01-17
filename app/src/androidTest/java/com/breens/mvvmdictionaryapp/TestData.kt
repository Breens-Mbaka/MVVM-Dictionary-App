package com.breens.mvvmdictionaryapp

import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModel
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import com.breens.mvvmdictionaryapp.home.data.remote.MeaningPresentationModel

val definitionsExclamationTestData = listOf(
    DefinitionPresentationModel(
        definition = "used as a greeting or to begin a phone conversation.",
    )
)

val definitionsNounTestData = listOf(
    DefinitionPresentationModel(
        definition = "an utterance of ‘hello’; a greeting.",
    )
)

val definitionsVerbTestData = listOf(
    DefinitionPresentationModel(
        definition = "say or shout ‘hello’.",
    )
)

val meaningsTestData = listOf(
    MeaningPresentationModel(
        definitions = definitionsExclamationTestData,
        partOfSpeech = "exclamation"
    ),
    MeaningPresentationModel(
        definitions = definitionsNounTestData,
        partOfSpeech = "noun"
    ),
    MeaningPresentationModel(
        definitions = definitionsVerbTestData,
        partOfSpeech = "verb"
    )
)

val definitionsTestData = listOf(
    DefinitionPresentationModelItem(
        meanings = meaningsTestData,
        phonetic = "həˈləʊ",
        word = "hello"
    )
)

