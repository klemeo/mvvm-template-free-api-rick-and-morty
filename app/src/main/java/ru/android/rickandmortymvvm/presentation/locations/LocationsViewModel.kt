package ru.android.rickandmortymvvm.presentation.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.rickandmortymvvm.base.interactor.Interactor
import ru.android.rickandmortymvvm.base.platform.BaseViewModel
import ru.android.rickandmortymvvm.base.utils.io
import ru.android.rickandmortymvvm.base.utils.ui
import ru.android.rickandmortymvvm.domain.interactor.LocationsInteractor
import ru.android.rickandmortymvvm.presentation.model.mapper.LocationsVMMapper
import ru.android.rickandmortymvvm.presentation.state.LocationsVS

class LocationsViewModel(
    private val locationsInteractor: LocationsInteractor
) : BaseViewModel() {

    val viewLocationsState: LiveData<LocationsVS> get() = mViewLocationsState
    private val mViewLocationsState = MutableLiveData<LocationsVS>()

    private val locationVMMapper by lazy { LocationsVMMapper() }

    fun getLocations() {
        if (viewLocationsState.value == null) {
            viewModelScope.launch {
                mViewLocationsState.value = LocationsVS.ShowLoader(true)
                try {
                    io {
                        locationsInteractor.execute(
                            Interactor.None
                        )
                            .collect {
                                ui {
                                    mViewLocationsState.value =
                                        LocationsVS.AddLocations(locationVMMapper.map(it))
                                }
                            }
                    }
                } catch (e: Exception) {
                    ui {
                        mViewLocationsState.value = LocationsVS.Error(e.message)
                    }
                }
                mViewLocationsState.value = LocationsVS.ShowLoader(false)
            }
        }
    }
}