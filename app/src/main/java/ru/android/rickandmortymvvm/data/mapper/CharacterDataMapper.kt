package ru.android.rickandmortymvvm.data.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.character_body.*
import ru.android.rickandmortymvvm.data.model.character_responses.*

class CharacterDataMapper : Mapper<CharacterResultResponsesBodyData, CharacterResultResponsesBody> {

    override fun map(origin: CharacterResultResponsesBodyData) = CharacterResultResponsesBody(
        created = origin.created,
        episode = origin.episode,
        gender = origin.gender,
        id = origin.id,
        image = origin.image,
        location = origin.location.let { locationData ->
            LocationResponsesBody(
                name = locationData?.name,
                url = locationData?.url
            )
        },
        name = origin.name,
        origin = origin.origin.let { originData ->
            OriginResponsesBody(
                name = originData?.name,
                url = originData?.url
            )
        },
        species = origin.species,
        status = origin.status,
        type = origin.type,
        url = origin.url,
    )

}