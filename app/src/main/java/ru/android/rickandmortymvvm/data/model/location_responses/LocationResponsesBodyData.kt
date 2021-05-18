package ru.android.rickandmortymvvm.data.model.location_responses


import com.google.gson.annotations.SerializedName

data class LocationResponsesBodyData(
    @SerializedName("info")
    val info: InfoResponsesBodyData?,
    @SerializedName("results")
    val results: List<LocationResultResponsesBodyData>?
)