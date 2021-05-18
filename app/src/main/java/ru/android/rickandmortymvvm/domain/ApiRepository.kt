package ru.android.rickandmortymvvm.domain

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResultResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResultResponsesBody

interface ApiRepository {

    fun getCharacters(): Flow<CharacterResponsesBody>

    fun getCharacter(id: Int): Flow<CharacterResultResponsesBody>

    fun getLocations(): Flow<LocationResponsesBody>

    fun getLocation(id: Int): Flow<LocationResultResponsesBody>

    fun getEpisodes(): Flow<EpisodeResponsesBody>

    fun getEpisode(id: Int): Flow<EpisodeResultResponsesBody>

}