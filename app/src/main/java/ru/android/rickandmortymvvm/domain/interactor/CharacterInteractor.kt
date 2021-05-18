package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResultResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class CharacterInteractor(
    private val apiRepository: ApiRepository
) : Interactor<CharacterInteractor.Params, Flow<CharacterResultResponsesBody>> {

    override fun execute(params: Params): Flow<CharacterResultResponsesBody> {
        return apiRepository.getCharacter(
            id = params.id
        )
    }

    data class Params(
        val id: Int
    )

}