package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.location_body.LocationResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class LocationsInteractor(
    private val apiRepository: ApiRepository
) : Interactor<Interactor.None, Flow<LocationResponsesBody>> {

    override fun execute(params: Interactor.None): Flow<LocationResponsesBody> {
        return apiRepository.getLocations()
    }

}