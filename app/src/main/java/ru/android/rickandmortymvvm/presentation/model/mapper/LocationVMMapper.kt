package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.presentation.model.location.InfoBody
import ru.android.rickandmortymvvm.presentation.model.location.LocationBody
import ru.android.rickandmortymvvm.presentation.model.location.ResultBody

class LocationVMMapper : Mapper<LocationResponsesBody, LocationBody> {

    override fun map(origin: LocationResponsesBody) = LocationBody(
        info = origin.info.let { info ->
            InfoBody(
                count = info?.count,
                next = info?.next,
                pages = info?.pages,
                prev = info?.prev,
            )
        },
        results = origin.results?.map { result ->
            ResultBody(
                created = result.created,
                dimension = result.dimension,
                id = result.id,
                name = result.name,
                residents = result.residents,
                type = result.type,
                url = result.url
            )
        }
    )
}