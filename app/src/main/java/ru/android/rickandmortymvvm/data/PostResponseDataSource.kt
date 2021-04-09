package ru.android.rickandmortymvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResponsesBodyData

class PostResponseDataSource {

    fun getCharacters(): Flow<CharacterResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getCharacters()
        )
    }

    fun getLocations(): Flow<LocationResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getLocations()
        )
    }

    fun getEpisodes(): Flow<EpisodeResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getEpisodes()
        )
    }

}