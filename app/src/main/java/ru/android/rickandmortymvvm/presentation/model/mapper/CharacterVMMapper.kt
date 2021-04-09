package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.presentation.model.character.*

class CharacterVMMapper : Mapper<CharacterResponsesBody, CharacterBody> {

    override fun map(origin: CharacterResponsesBody) = CharacterBody(
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
                episode = result.episode,
                gender = result.gender,
                id = result.id,
                image = result.image,
                location = result.location.let { location ->
                    LocationBody(
                        name = location?.name,
                        url = location?.url
                    )
                },
                name = result.name,
                origin = result.origin.let { origin ->
                    OriginBody(
                        name = origin?.name,
                        url = origin?.url
                    )
                },
                species = result.species,
                status = result.status,
                type = result.type,
                url = result.url,
            )
        }
    )
}