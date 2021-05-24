package ru.android.rickandmortymvvm.presentation

interface MainActivityContract : CharactersScreen, CharacterScreenOne, CharacterScreenTwo,
    LocationsScreen,
    LocationScreen,
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

interface LocationScreen {
    fun openLocationScreen(id: Int)
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
