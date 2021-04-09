package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.character.CharacterBody

sealed class CharactersVS {
    class AddCharacters(val charactersVM: CharacterBody) : CharactersVS()
    class Error(val message: String?) : CharactersVS()
    class ShowLoader(val showLoader: Boolean) : CharactersVS()
}
