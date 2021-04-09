package ru.android.rickandmortymvvm.app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.android.rickandmortymvvm.data.ApiRepositoryImpl
import ru.android.rickandmortymvvm.data.PostResponseDataSource
import ru.android.rickandmortymvvm.domain.ApiRepository
import ru.android.rickandmortymvvm.domain.interactor.CharactersInteractor
import ru.android.rickandmortymvvm.domain.interactor.EpisodesInteractor
import ru.android.rickandmortymvvm.domain.interactor.LocationsInteractor
import ru.android.rickandmortymvvm.presentation.character.CharactersViewModel
import ru.android.rickandmortymvvm.presentation.episode.EpisodesViewModel
import ru.android.rickandmortymvvm.presentation.location.LocationsViewModel

private val postModule = module {

    //region ViewModel
    viewModel {
        CharactersViewModel(
            charactersInteractor = get()
        )
    }

    viewModel {
        EpisodesViewModel(
            episodesInteractor = get()
        )
    }

    viewModel {
        LocationsViewModel(
            locationsInteractor = get()
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
        EpisodesInteractor(
            apiRepository = get()
        )
    }
    single {
        LocationsInteractor(
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