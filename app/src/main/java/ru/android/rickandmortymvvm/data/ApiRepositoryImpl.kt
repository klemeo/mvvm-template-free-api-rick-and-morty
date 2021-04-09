package ru.android.rickandmortymvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.android.rickandmortymvvm.data.mapper.CharacterDataMapper
import ru.android.rickandmortymvvm.data.mapper.EpisodeDataMapper
import ru.android.rickandmortymvvm.data.mapper.LocationDataMapper
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class ApiRepositoryImpl(
    private val apiResponseDataSource: PostResponseDataSource
) : ApiRepository {

    private val characterDataMapper by lazy { CharacterDataMapper() }
    private val episodeDataMapper by lazy { EpisodeDataMapper() }
    private val locationDataMapper by lazy { LocationDataMapper() }


    override fun getCharacters(): Flow<CharacterResponsesBody> =
        apiResponseDataSource.getCharacters().map {
            characterDataMapper.map(it)
        }


    override fun getLocations(): Flow<LocationResponsesBody> =
        apiResponseDataSource.getLocations().map {
            locationDataMapper.map(it)
        }


    override fun getEpisodes(): Flow<EpisodeResponsesBody> =
        apiResponseDataSource.getEpisodes().map {
            episodeDataMapper.map(it)
        }

}