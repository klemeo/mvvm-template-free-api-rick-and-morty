package ru.android.rickandmortymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import ru.android.rickandmortymvvm.presentation.MainActivityContract
import ru.android.rickandmortymvvm.presentation.home.HomeFragmentDirections

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

    override fun openLocationsScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToLocationsFragment()
        )
    }

    override fun openEpisodesScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToEpisodesFragment()
        )
    }
}