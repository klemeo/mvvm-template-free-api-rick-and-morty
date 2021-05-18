package ru.android.rickandmortymvvm.presentation.state

import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeBody

sealed class EpisodesVS {
    class AddEpisodes(val episodesVM: EpisodeBody) : EpisodesVS()
    class Error(val message: String?) : EpisodesVS()
    class ShowLoader(val showLoader: Boolean) : EpisodesVS()
}
