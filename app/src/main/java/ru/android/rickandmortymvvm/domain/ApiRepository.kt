package ru.android.rickandmortymvvm.domain

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody

interface ApiRepository {

    fun getCharacters(): Flow<CharacterResponsesBody>

    fun getLocations(): Flow<LocationResponsesBody>

    fun getEpisodes(): Flow<EpisodeResponsesBody>

}