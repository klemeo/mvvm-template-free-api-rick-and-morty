package ru.android.rickandmortymvvm.data.model.location_body



data class LocationResultResponsesBody(
    val created: String?,
    val dimension: String?,
    val id: Int?,
    val name: String?,
    val residents: List<String>?,
    val type: String?,
    val url: String?
)