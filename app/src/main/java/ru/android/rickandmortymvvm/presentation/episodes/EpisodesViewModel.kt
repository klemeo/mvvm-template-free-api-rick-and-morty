package ru.android.rickandmortymvvm.presentation.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.EpisodesInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.EpisodesVMMapper
import ru.android.rickandmortymvvm.presentation.state.EpisodesVS

class EpisodesViewModel(
    private val episodesInteractor: EpisodesInteractor
) : BaseViewModel() {

    val viewEpisodesState: LiveData<EpisodesVS> get() = mViewEpisodesState
    private val mViewEpisodesState = MutableLiveData<EpisodesVS>()

    private val episodeVMMapper by lazy { EpisodesVMMapper() }

    fun getEpisodes(page: Int? = null) {
        viewModelScope.launch {
            mViewEpisodesState.value = EpisodesVS.ShowLoader(true)
            try {
                io {
                    episodesInteractor.execute(
                        EpisodesInteractor.Params(
                            page = page
                        )
                    )
                        .collect {
                            ui {
                                mViewEpisodesState.value =
                                    EpisodesVS.AddEpisodes(episodeVMMapper.map(it))
                            }
                        }
                }
            } catch (e: Exception) {
                ui {
                    mViewEpisodesState.value = EpisodesVS.Error(e.message)
                }
            }
            mViewEpisodesState.value = EpisodesVS.ShowLoader(false)
        }
    }

}