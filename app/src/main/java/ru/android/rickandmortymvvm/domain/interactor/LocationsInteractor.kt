package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class LocationsInteractor(
    private val apiRepository: ApiRepository
) : Interactor<LocationsInteractor.Params, Flow<LocationResponsesBody>> {

    override fun execute(params: Params): Flow<LocationResponsesBody> {
        return apiRepository.getLocations(params.page)
    }

    data class Params(
        val page: Int?
    )

}