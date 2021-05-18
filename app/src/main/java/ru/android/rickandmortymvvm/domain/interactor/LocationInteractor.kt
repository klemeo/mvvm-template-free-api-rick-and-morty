package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.location_body.LocationResultResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class LocationInteractor(
    private val apiRepository: ApiRepository
) : Interactor<LocationInteractor.Params, Flow<LocationResultResponsesBody>> {

    override fun execute(params: Params): Flow<LocationResultResponsesBody> {
        return apiRepository.getLocation(
            id = params.id
        )
    }

    data class Params(
        val id: Int
    )

}