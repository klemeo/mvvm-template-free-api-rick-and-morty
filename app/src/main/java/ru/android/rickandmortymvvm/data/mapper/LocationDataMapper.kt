package ru.android.rickandmortymvvm.data.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.location_body.LocationResultResponsesBody
import ru.android.rickandmortymvvm.data.model.location_responses.LocationResultResponsesBodyData

class LocationDataMapper : Mapper<LocationResultResponsesBodyData, LocationResultResponsesBody> {

    override fun map(origin: LocationResultResponsesBodyData) = LocationResultResponsesBody(
        created = origin.created,
        dimension = origin.dimension,
        id = origin.id,
        name = origin.name,
        residents = origin.residents,
        type = origin.type,
        url = origin.url
    )
}