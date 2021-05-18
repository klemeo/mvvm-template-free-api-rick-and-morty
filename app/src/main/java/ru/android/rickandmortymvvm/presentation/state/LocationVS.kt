package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.location.LocationResultBody

sealed class LocationVS {
    class AddLocation(val locationsVM: LocationResultBody) : LocationVS()
    class Error(val message: String?) : LocationVS()
    class ShowLoader(val showLoader: Boolean) : LocationVS()
}
