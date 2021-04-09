package ru.android.rickandmortymvvm.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.data.model.character_body.CharacterResponsesBody
import ru.android.rickandmortymvvm.domain.ApiRepository

class CharactersInteractor(
    private val apiRepository: ApiRepository
) : Interactor<Interactor.None, Flow<CharacterResponsesBody>> {

    override fun execute(params: Interactor.None): Flow<CharacterResponsesBody> {
        return apiRepository.getCharacters()
    }

}