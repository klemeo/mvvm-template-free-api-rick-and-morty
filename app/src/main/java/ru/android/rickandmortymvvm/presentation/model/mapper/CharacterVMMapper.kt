package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResultResponsesBody
import ru.android.rickandmortymvvm.presentation.model.character.*

class CharacterVMMapper : Mapper<CharacterResultResponsesBody, CharacterResultBody> {

    override fun map(origin: CharacterResultResponsesBody) = CharacterResultBody(
        created = origin.created,
        episode = origin.episode,
        gender = origin.gender,
        id = origin.id,
        image = origin.image,
        location = origin.location.let { location ->
            LocationBody(
                name = location?.name,
                url = location?.url
            )
        },
        name = origin.name,
        origin = origin.origin.let {
            OriginBody(
                name = it?.name,
                url = it?.url
            )
        },
        species = origin.species,
        status = origin.status,
        type = origin.type,
        url = origin.url,
    )
}