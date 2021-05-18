package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeResultBody

class EpisodeVMMapper : Mapper<EpisodeResultResponsesBody, EpisodeResultBody> {

    override fun map(origin: EpisodeResultResponsesBody) = EpisodeResultBody(
        airDate = origin.airDate,
        characters = origin.characters,
        created = origin.created,
        episode = origin.episode,
        id = origin.id,
        name = origin.name,
        url = origin.url
    )
}