package ru.android.rickandmortymvvm.presentation.model.character

data class ResultBody(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: LocationBody?,
    val name: String?,
    val origin: OriginBody?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)