package ru.android.rickandmortymvvm.presentation.model.location

data class LocationBody(
    val info: InfoBody?,
    val results: List<LocationResultBody>?
)