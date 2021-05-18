package ru.android.rickandmortymvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.android.rickandmortymvvm.data.mapper.*
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResultResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResultResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class ApiRepositoryImpl(
    private val apiResponseDataSource: PostResponseDataSource
) : ApiRepository {

    private val charactersDataMapper by lazy { CharactersDataMapper() }
    private val characterDataMapper by lazy { CharacterDataMapper() }

    private val episodesDataMapper by lazy { EpisodesDataMapper() }
    private val episodeDataMapper by lazy { EpisodeDataMapper() }

    private val locationsDataMapper by lazy { LocationsDataMapper() }
    private val locationDataMapper by lazy { LocationDataMapper() }


    override fun getCharacters(): Flow<CharacterResponsesBody> =
        apiResponseDataSource.getCharacters().map {
            charactersDataMapper.map(it)
        }

    override fun getCharacter(id: Int): Flow<CharacterResultResponsesBody> =
        apiResponseDataSource.getCharacter(id).map {
            characterDataMapper.map(it)
        }

    override fun getLocations(): Flow<LocationResponsesBody> =
        apiResponseDataSource.getLocations().map {
            locationsDataMapper.map(it)
        }

    override fun getLocation(id: Int): Flow<LocationResultResponsesBody> =
        apiResponseDataSource.getLocation(id).map {
            locationDataMapper.map(it)
        }

    override fun getEpisodes(): Flow<EpisodeResponsesBody> =
        apiResponseDataSource.getEpisodes().map {
            episodesDataMapper.map(it)
        }

    override fun getEpisode(id: Int): Flow<EpisodeResultResponsesBody> =
        apiResponseDataSource.getEpisode(id).map {
            episodeDataMapper.map(it)
        }

}