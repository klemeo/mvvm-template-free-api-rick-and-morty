package ru.android.rickandmortymvvm.presentation

interface MainActivityContract : CharactersScreen, CharacterScreenOne, CharacterScreenTwo,
    LocationsScreen,
    LocationScreenOne, LocationScreenTwo,
    EpisodesScreen, EpisodeScreenOne, EpisodeScreenTwo

interface CharactersScreen {
    fun openCharactersScreen()
}

interface CharacterScreenOne {
    fun openCharacterScreenOne(id: Int)
}

interface CharacterScreenTwo {
    fun openCharacterScreenTwo(id: Int)
}

interface LocationsScreen {
    fun openLocationsScreen()
}

interface LocationScreenOne {
    fun openLocationScreenOne(id: Int)
}

interface LocationScreenTwo {
    fun openLocationScreenTwo(id: Int)
}

interface EpisodesScreen {
    fun openEpisodesScreen()
}

interface EpisodeScreenOne {
    fun openEpisodeScreenOne(id: Int)
}

interface EpisodeScreenTwo {
    fun openEpisodeScreenTwo(id: Int)
}
