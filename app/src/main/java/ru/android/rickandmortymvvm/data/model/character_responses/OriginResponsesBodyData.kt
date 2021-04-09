package ru.android.rickandmortymvvm.data.model.character_responses


import com.google.gson.annotations.SerializedName

data class OriginResponsesBodyData(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)