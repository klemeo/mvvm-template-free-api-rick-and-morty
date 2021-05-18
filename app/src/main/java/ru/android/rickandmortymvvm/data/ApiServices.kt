package ru.android.rickandmortymvvm.data

import retrofit2.http.*
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResponsesBodyData
import ru.android.rickandmortymvvm.data.model.character_responses.CharacterResultResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResponsesBodyData
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResultResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResponsesBodyData
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResultResponsesBodyData

interface ApiServices {

    @GET("character")
    suspend fun getCharacters(): CharacterResponsesBodyData

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterResultResponsesBodyData

    @GET("location")
    suspend fun getLocations(): LocationResponsesBodyData

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: Int
    ): LocationResultResponsesBodyData

    @GET("episode")
    suspend fun getEpisodes(): EpisodeResponsesBodyData

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): EpisodeResultResponsesBodyData

}