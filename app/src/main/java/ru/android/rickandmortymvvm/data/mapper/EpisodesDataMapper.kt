package ru.android.rickandmortymvvm.data.mapper

import ru.android.rickandmortymvvm.base.mapper.Mapper
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.InfoResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.data.model.episode_responses.EpisodeResponsesBodyData

class EpisodesDataMapper : Mapper<EpisodeResponsesBodyData, EpisodeResponsesBody> {

    override fun map(origin: EpisodeResponsesBodyData) = EpisodeResponsesBody(
        info = origin.info.let { infoData ->
            InfoResponsesBody(
                count = infoData?.count,
                next = infoData?.next,
                pages = infoData?.pages,
                prev = infoData?.prev,
            )
        },
        results = origin.results?.map { resultData ->
            EpisodeResultResponsesBody(
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