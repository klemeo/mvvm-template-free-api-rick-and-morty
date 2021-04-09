package ru.android.rickandmortymvvm.data.model.character_responses


import com.google.gson.annotations.SerializedName

data class CharacterResponsesBodyData(
    @SerializedName("info")
    val info: InfoResponsesBodyData?,
    @SerializedName("results")
    val results: List<ResultResponsesBodyData>?
)