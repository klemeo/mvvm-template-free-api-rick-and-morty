package ru.android.rickandmortymvvm.app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.android.rickandmortymvvm.data.ApiRepositoryImpl
import ru.android.rickandmortymvvm.data.PostResponseDataSource
import ru.android.rickandmortymvvm.domain.ApiRepository
import ru.android.rickandmortymvvm.domain.interactor.*
import ru.android.rickandmortymvvm.presentation.character.CharacterViewModel
import ru.android.rickandmortymvvm.presentation.characters.CharactersViewModel
import ru.android.rickandmortymvvm.presentation.episode.EpisodeViewModel
import ru.android.rickandmortymvvm.presentation.episodes.EpisodesViewModel
import ru.android.rickandmortymvvm.presentation.location.LocationViewModel
import ru.android.rickandmortymvvm.presentation.locations.LocationsViewModel

private val postModule = module {

    //region ViewModel
    viewModel {
        CharactersViewModel(
            charactersInteractor = get()
        )
    }

    viewModel {
        CharacterViewModel(
            characterInteractor = get()
        )
    }

    viewModel {
        EpisodesViewModel(
            episodesInteractor = get()
        )
    }

    viewModel {
        EpisodeViewModel(
            episodeInteractor = get()
        )
    }

    viewModel {
        LocationsViewModel(
            locationsInteractor = get()
        )
    }

    viewModel {
        LocationViewModel(
            locationInteractor = get()
        )
    }
    //endregion

    //region Interactor
    single {
        CharactersInteractor(
            apiRepository = get()
        )
    }
    single {
        CharacterInteractor(
            apiRepository = get()
        )
    }
    single {
        EpisodesInteractor(
            apiRepository = get()
        )
    }
    single {
        EpisodeInteractor(
            apiRepository = get()
        )
    }
    single {
        LocationsInteractor(
            apiRepository = get()
        )
    }
    single {
        LocationInteractor(
            apiRepository = get()
        )
    }
    //endregion

    //region Repository
    single<ApiRepository> {
        ApiRepositoryImpl(
            apiResponseDataSource = get()
        )
    }
    //endregion

    //region Datastore
    single {
        PostResponseDataSource()
    }
    //endregion
}

val modules = listOf(postModule)