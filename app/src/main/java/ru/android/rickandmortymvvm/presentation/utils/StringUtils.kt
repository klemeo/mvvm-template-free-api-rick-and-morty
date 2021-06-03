package ru.android.rickandmortymvvm.presentation.utils

fun String.pageCharacters(): Int = this.replace(
    "https://rickandmortyapi.com/api/character?page=",
    ""
).toInt()

fun String.pageEpisodes(): Int = this.replace(
    "https://rickandmortyapi.com/api/episode?page=",
    ""
).toInt()

fun String.pageLocations(): Int = this.replace(
    "https://rickandmortyapi.com/api/location?page=",
    ""
).toInt()

fun String.pageCharacter(): Int = this.replace(
    "https://rickandmortyapi.com/api/character/",
    ""
).toInt()

fun String.pageEpisode(): Int = this.replace(
    "https://rickandmortyapi.com/api/episode/",
    ""
).toInt()