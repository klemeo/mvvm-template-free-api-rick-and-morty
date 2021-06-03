package ru.android.rickandmortymvvm.presentation.episodes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_episodes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.databinding.FragmentEpisodesBinding
import ru.android.rickandmortymvvm.presentation.EpisodeScreenOne
import ru.android.rickandmortymvvm.presentation.state.EpisodesVS
import ru.android.rickandmortymvvm.presentation.utils.gone
import ru.android.rickandmortymvvm.presentation.utils.invisible
import ru.android.rickandmortymvvm.presentation.utils.pageEpisodes
import ru.android.rickandmortymvvm.presentation.utils.visible

class EpisodesFragment : Fragment(), EpisodesAdapter.Listener {

    private val viewModel: EpisodesViewModel by viewModel()
    private val episodesAdapter = EpisodesAdapter()
    private lateinit var episodeListener: EpisodeScreenOne

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        episodeListener =
            FragmentListenerUtils.getFragmentListener(this, EpisodeScreenOne::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentEpisodesBinding>(
            inflater,
            R.layout.fragment_episodes,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        episodesAdapter.setListener(this)
        viewModel.getEpisodes()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = episodesAdapter
        }

        nextButton.gone()
        backButton.gone()

        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
        nextButton.setOnClickListener {
            viewModel.getEpisodes(nextPage)
        }

        backButton.setOnClickListener {
            viewModel.getEpisodes(prevPage)
        }
    }

    private fun observeViewModel() {
        viewModel.viewEpisodesState.observe(viewLifecycleOwner, {
            when (it) {
                is EpisodesVS.AddEpisodes -> {
                    it.episodesVM.results.let { character ->
                        if (character != null) {
                            episodesAdapter.add(character)
                        }
                    }
                    nextPage =
                        if (it.episodesVM.info?.next != null) it.episodesVM.info.next
                            .pageEpisodes() else null
                    when {
                        nextPage != null -> nextButton.visible()
                        nextPage == null -> nextButton.gone()
                    }
                    prevPage =
                        if (it.episodesVM.info?.prev != null) it.episodesVM.info.prev.toString()
                            .pageEpisodes() else null
                    when {
                        prevPage != null -> backButton.visible()
                        prevPage == null -> backButton.gone()
                    }
                }
                is EpisodesVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visible()
                        recyclerView.invisible()
                    } else {
                        pbPost.invisible()
                        recyclerView.visible()
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is EpisodesVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

    override fun onPostClicked(id: Int) {
        episodeListener.openEpisodeScreenOne(id)
    }

}