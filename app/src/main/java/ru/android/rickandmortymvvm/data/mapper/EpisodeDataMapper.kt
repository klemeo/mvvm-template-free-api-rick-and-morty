package ru.android.rickandmortymvvm.data.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResultResponsesBodyData

class EpisodeDataMapper : Mapper<EpisodeResultResponsesBodyData, EpisodeResultResponsesBody> {

    override fun map(origin: EpisodeResultResponsesBodyData) = EpisodeResultResponsesBody(
        airDate = origin.airDate,
        characters = origin.characters,
        created = origin.created,
        episode = origin.episode,
        id = origin.id,
        name = origin.name,
        url = origin.url
    )
}