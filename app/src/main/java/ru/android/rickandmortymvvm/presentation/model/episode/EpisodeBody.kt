package ru.android.rickandmortymvvm.presentation.model.episode

data class EpisodeBody(
    val info: InfoBody?,
    val results: List<EpisodeResultBody>?
)