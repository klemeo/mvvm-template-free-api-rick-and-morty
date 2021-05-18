package ru.android.rickandmortymvvm.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.CharacterInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.CharacterVMMapper
import ru.android.rickandmortymvvm.presentation.state.CharacterVS

class CharacterViewModel(
    private val characterInteractor: CharacterInteractor
) : BaseViewModel() {

    val viewCharacterState: LiveData<CharacterVS> get() = mViewCharacterState
    private val mViewCharacterState = MutableLiveData<CharacterVS>()

    private val characterMapper by lazy { CharacterVMMapper() }

    fun getCharacter(id: Int) {
        if (viewCharacterState.value == null) {
            viewModelScope.launch {
                mViewCharacterState.value = CharacterVS.ShowLoader(true)
                try {
                    io {
                        characterInteractor.execute(
                            CharacterInteractor.Params(
                                id = id
                            )
                        )
                            .collect {
                                ui {
                                    mViewCharacterState.value =
                                        CharacterVS.AddCharacter(characterMapper.map(it))
                                }
                            }
                    }
                } catch (e: Exception) {
                    ui {
                        mViewCharacterState.value = CharacterVS.Error(e.message)
                    }
                }
                mViewCharacterState.value = CharacterVS.ShowLoader(false)
            }
        }
    }

}