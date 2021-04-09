package ru.android.rickandmortymvvm.data.model.episode_responses


import com.google.gson.annotations.SerializedName

data class EpisodeResponsesBodyData(
    @SerializedName("info")
    val info: InfoResponsesBodyData?,
    @SerializedName("results")
    val results: List<ResultResponsesBodyData>?
)