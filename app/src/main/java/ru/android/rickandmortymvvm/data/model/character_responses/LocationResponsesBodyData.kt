package ru.android.rickandmortymvvm.data.model.character_responses


import com.google.gson.annotations.SerializedName

data class LocationResponsesBodyData(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)