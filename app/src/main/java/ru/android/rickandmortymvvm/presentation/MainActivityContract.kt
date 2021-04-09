package ru.android.rickandmortymvvm.presentation

interface MainActivityContract : CharactersScreen, LocationsScreen, EpisodesScreen

interface CharactersScreen {
    fun openCharactersScreen()
}

interface LocationsScreen {
    fun openLocationsScreen()
}

interface EpisodesScreen {
    fun openEpisodesScreen()
}
