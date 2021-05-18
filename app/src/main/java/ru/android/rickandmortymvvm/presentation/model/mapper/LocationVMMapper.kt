package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.location_body.LocationResultResponsesBody
import ru.android.rickandmortymvvm.presentation.model.location.LocationResultBody

class LocationVMMapper : Mapper<LocationResultResponsesBody, LocationResultBody> {

    override fun map(origin: LocationResultResponsesBody) = LocationResultBody(
        created = origin.created,
        dimension = origin.dimension,
        id = origin.id,
        name = origin.name,
        residents = origin.residents,
        type = origin.type,
        url = origin.url
    )

}