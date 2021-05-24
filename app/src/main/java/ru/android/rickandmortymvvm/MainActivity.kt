package ru.android.rickandmortymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import ru.android.rickandmortymvvm.presentation.MainActivityContract
import ru.android.rickandmortymvvm.presentation.character.CharacterFragmentDirections
import ru.android.rickandmortymvvm.presentation.characters.CharactersFragmentDirections
import ru.android.rickandmortymvvm.presentation.episode.EpisodeFragmentDirections
import ru.android.rickandmortymvvm.presentation.episodes.EpisodesFragmentDirections
import ru.android.rickandmortymvvm.presentation.home.HomeFragmentDirections
import ru.android.rickandmortymvvm.presentation.locations.LocationsFragmentDirections

class MainActivity : AppCompatActivity(), MainActivityContract {

    private val navHostFragmentId = R.id.main_nav_host_fragment
    private val navController by lazy { findNavController(navHostFragmentId) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openCharactersScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
        )
    }

    override fun openCharacterScreenOne(id: Int) {
        navController.navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(id)
        )
    }

    override fun openCharacterScreenTwo(id: Int) {
        navController.navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToCharacterFragment(id)
        )
    }

    override fun openLocationsScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToLocationsFragment()
        )
    }

    override fun openLocationScreen(id: Int) {
        navController.navigate(
            LocationsFragmentDirections.actionLocationsFragmentToLocationFragment(id)
        )
    }

    override fun openEpisodesScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToEpisodesFragment()
        )
    }

    override fun openEpisodeScreenOne(id: Int) {
        navController.navigate(
            EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeFragment(id)
        )
    }

    override fun openEpisodeScreenTwo(id: Int) {
        navController.navigate(
            CharacterFragmentDirections.actionCharacterFragmentToEpisodeFragment(id)
        )
    }

}