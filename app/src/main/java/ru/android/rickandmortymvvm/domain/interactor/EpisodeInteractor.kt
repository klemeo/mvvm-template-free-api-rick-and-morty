package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.episode_body.EpisodeResultResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class EpisodeInteractor(
    private val apiRepository: ApiRepository
) : Interactor<EpisodeInteractor.Params, Flow<EpisodeResultResponsesBody>> {

    override fun execute(params: Params): Flow<EpisodeResultResponsesBody> {
        return apiRepository.getEpisode(
            id = params.id
        )
    }

    data class Params(
        val id: Int
    )

}