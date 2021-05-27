package ru.android.rickandmortymvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResponsesBodyData
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResultResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResultResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResultResponsesBodyData

class PostResponseDataSource {

    fun getCharacters(page: Int?): Flow<CharacterResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getCharacters(page)
        )
    }

    fun getCharacter(id: Int): Flow<CharacterResultResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getCharacter(id)
        )
    }

    fun getLocations(page: Int?): Flow<LocationResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getLocations(page)
        )
    }

    fun getLocation(id: Int): Flow<LocationResultResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getLocation(id)
        )
    }

    fun getEpisodes(page: Int?): Flow<EpisodeResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getEpisodes(page)
        )
    }

    fun getEpisode(id: Int): Flow<EpisodeResultResponsesBodyData> = flow {
        emit(
            PostApiClient.getApiClient().getEpisode(id)
        )
    }

}