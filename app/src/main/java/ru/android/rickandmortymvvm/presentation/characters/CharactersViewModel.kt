package ru.android.rickandmortymvvm.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.CharactersInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.CharactersVMMapper
import ru.android.rickandmortymvvm.presentation.state.CharactersVS

class CharactersViewModel(
    private val charactersInteractor: CharactersInteractor
) : BaseViewModel() {

    val viewCharactersState: LiveData<CharactersVS?> get() = mViewCharactersState
    private val mViewCharactersState = MutableLiveData<CharactersVS?>()

    private val charactersMapper by lazy { CharactersVMMapper() }

    fun getCharacters(page: Int? = null) {
        viewModelScope.launch {
            mViewCharactersState.value = CharactersVS.ShowLoader(true)
            try {
                io {
                    charactersInteractor.execute(
                        CharactersInteractor.Params(
                            page = page
                        )
                    )
                        .collect {
                            ui {
                                mViewCharactersState.value =
                                    CharactersVS.AddCharacters(charactersMapper.map(it))
                            }
                        }
                }
            } catch (e: Exception) {
                ui {
                    mViewCharactersState.value = CharactersVS.Error(e.message)
                }
            }
            mViewCharactersState.value = CharactersVS.ShowLoader(false)
        }
    }


}