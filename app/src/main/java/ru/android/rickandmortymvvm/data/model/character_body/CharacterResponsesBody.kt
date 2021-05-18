package ru.android.rickandmortymvvm.data.model.character_body

data class CharacterResponsesBody(
    val info: InfoResponsesBody?,
    val results: List<CharacterResultResponsesBody>?
)