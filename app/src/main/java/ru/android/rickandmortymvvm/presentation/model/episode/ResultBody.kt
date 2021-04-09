package ru.android.rickandmortymvvm.presentation.model.episode

data class ResultBody(
    val airDate: String?,
    val characters: List<String>?,
    val created: String?,
    val episode: String?,
    val id: Int?,
    val name: String?,
    val url: String?
)