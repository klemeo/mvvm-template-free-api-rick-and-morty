package ru.android.rickandmortymvvm.data.model.character_responses


import com.google.gson.annotations.SerializedName

data class ResultResponsesBodyData(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: List<String>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: LocationResponsesBodyData?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: OriginResponsesBodyData?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)