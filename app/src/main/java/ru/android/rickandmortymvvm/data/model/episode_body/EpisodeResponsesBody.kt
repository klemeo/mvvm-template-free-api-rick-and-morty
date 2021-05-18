package ru.android.rickandmortymvvm.data.model.episode_body

data class EpisodeResponsesBody(
    val info: InfoResponsesBody?,
    val results: List<EpisodeResultResponsesBody>?
)