package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.location.LocationBody

sealed class LocationsVS {
    class AddLocations(val locationsVM: LocationBody) : LocationsVS()
    class Error(val message: String?) : LocationsVS()
    class ShowLoader(val showLoader: Boolean) : LocationsVS()
}