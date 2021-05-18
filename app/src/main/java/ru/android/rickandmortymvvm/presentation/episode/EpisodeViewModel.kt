package ru.android.rickandmortymvvm.presentation.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.EpisodeInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.EpisodeVMMapper
import ru.android.rickandmortymvvm.presentation.state.EpisodeVS

class EpisodeViewModel(
    private val episodeInteractor: EpisodeInteractor
) : BaseViewModel() {

    val viewEpisodeState: LiveData<EpisodeVS> get() = mViewEpisodeState
    private val mViewEpisodeState = MutableLiveData<EpisodeVS>()

    private val episodeVMMapper by lazy { EpisodeVMMapper() }

    fun getEpisode(id: Int) {
        if (viewEpisodeState.value == null) {
            viewModelScope.launch {
                mViewEpisodeState.value = EpisodeVS.ShowLoader(true)
                try {
                    io {
                        episodeInteractor.execute(
                            EpisodeInteractor.Params(
                                id = id
                            )
                        )
                            .collect {
                                ui {
                                    mViewEpisodeState.value =
                                        EpisodeVS.AddEpisode(episodeVMMapper.map(it))
                                }
                            }
                    }
                } catch (e: Exception) {
                    ui {
                        mViewEpisodeState.value = EpisodeVS.Error(e.message)
                    }
                }
                mViewEpisodeState.value = EpisodeVS.ShowLoader(false)
            }
        }
    }

}