package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.character.CharacterResultBody

sealed class CharacterVS {
    class AddCharacter(val charactersVM: CharacterResultBody) : CharacterVS()
    class Error(val message: String?) : CharacterVS()
    class ShowLoader(val showLoader: Boolean) : CharacterVS()
}
