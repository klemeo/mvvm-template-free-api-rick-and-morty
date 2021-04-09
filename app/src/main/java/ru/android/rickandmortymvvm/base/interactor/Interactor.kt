package ru.android.rickandmortymvvm.base.interactor

interface Interactor<in Params, out Type> {

    fun execute(params: Params): Type

    object None
}