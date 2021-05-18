package ru.android.rickandmortymvvm.presentation

interface MainActivityContract : CharactersScreen, CharacterScreen, LocationsScreen, LocationScreen,
    EpisodesScreen, EpisodeScreen

interface CharactersScreen {
    fun openCharactersScreen()
}

interface CharacterScreen {
    fun openCharacterScreen(id: Int)
}

interface LocationsScreen {
    fun openLocationsScreen()
}

interface LocationScreen {
    fun openLocationScreen(id: Int)
}

interface EpisodesScreen {
    fun openEpisodesScreen()
}

interface EpisodeScreen {
    fun openEpisodeScreen(id: Int)
}
