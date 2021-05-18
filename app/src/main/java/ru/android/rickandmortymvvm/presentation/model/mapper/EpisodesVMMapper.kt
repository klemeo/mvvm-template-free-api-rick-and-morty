package ru.android.rickandmortymvvm.presentation.model.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeBody
import ru.android.rickandmortymvvm.presentation.model.episode.InfoBody
import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeResultBody

class EpisodesVMMapper : Mapper<EpisodeResponsesBody, EpisodeBody> {

    override fun map(origin: EpisodeResponsesBody) = EpisodeBody(
        info = origin.info.let { infoData ->
            InfoBody(
                count = infoData?.count,
                next = infoData?.next,
                pages = infoData?.pages,
                prev = infoData?.prev,
            )
        },
        results = origin.results?.map { resultData ->
            EpisodeResultBody(
                airDate = resultData.airDate,
                characters = resultData.characters,
                created = resultData.created,
                episode = resultData.episode,
                id = resultData.id,
                name = resultData.name,
                url = resultData.url
            )
        }
    )
}