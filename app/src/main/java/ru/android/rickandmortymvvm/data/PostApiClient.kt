package ru.android.rickandmortymvvm.data

import ru.android.rickandmortymvvm.base.network.BaseApiClient


object PostApiClient : BaseApiClient<ApiServices>(ApiServices::class.java)