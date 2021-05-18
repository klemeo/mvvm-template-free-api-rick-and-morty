package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeResultBody

sealed class EpisodeVS {
    class AddEpisode(val episodesVM: EpisodeResultBody) : EpisodeVS()
    class Error(val message: String?) : EpisodeVS()
    class ShowLoader(val showLoader: Boolean) : EpisodeVS()
}
