package ru.android.rickandmortymvvm.presentation.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.LocationInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.LocationVMMapper
import ru.android.rickandmortymvvm.presentation.state.LocationVS

class LocationViewModel(
    private val locationInteractor: LocationInteractor
) : BaseViewModel() {

    val viewLocationState: LiveData<LocationVS> get() = mViewLocationState
    private val mViewLocationState = MutableLiveData<LocationVS>()

    private val locationVMMapper by lazy { LocationVMMapper() }

    fun getLocation(id: Int) {
        if (viewLocationState.value == null) {
            viewModelScope.launch {
                mViewLocationState.value = LocationVS.ShowLoader(true)
                try {
                    io {
                        locationInteractor.execute(
                            LocationInteractor.Params(
                                id = id
                            )
                        )
                            .collect {
                                ui {
                                    mViewLocationState.value =
                                        LocationVS.AddLocation(locationVMMapper.map(it))
                                }
                            }
                    }
                } catch (e: Exception) {
                    ui {
                        mViewLocationState.value = LocationVS.Error(e.message)
                    }
                }
                mViewLocationState.value = LocationVS.ShowLoader(false)
            }
        }
    }
}