package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class EpisodesInteractor(
    private val apiRepository: ApiRepository
) : Interactor<EpisodesInteractor.Params, Flow<EpisodeResponsesBody>> {

    override fun execute(params: Params): Flow<EpisodeResponsesBody> {
        return apiRepository.getEpisodes(params.page)
    }

    data class Params(
        val page: Int?
    )

}