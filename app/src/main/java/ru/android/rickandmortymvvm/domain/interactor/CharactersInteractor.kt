package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class CharactersInteractor(
    private val apiRepository: ApiRepository
) : Interactor<CharactersInteractor.Params, Flow<CharacterResponsesBody>> {

    override fun execute(params: Params): Flow<CharacterResponsesBody> {
        return apiRepository.getCharacters(params.page)
    }

    data class Params(
        val page: Int?
    )

}