package ru.android.rickandmortymvvm.data

import retrofit2.http.*
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResponsesBodyData

interface ApiServices {

    @GET("character")
    suspend fun getCharacters(): CharacterResponsesBodyData

    @GET("location")
    suspend fun getLocations(): LocationResponsesBodyData

    @GET("episode")
    suspend fun getEpisodes(): EpisodeResponsesBodyData

}