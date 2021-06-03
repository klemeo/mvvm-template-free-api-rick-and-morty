package ru.android.rickandmortymvvm.presentation.episode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_episode.*
import kotlinx.android.synthetic.main.fragment_episode.buttonBack
import kotlinx.android.synthetic.main.fragment_episode.linearLayout
import kotlinx.android.synthetic.main.fragment_episode.pbPost
import kotlinx.android.synthetic.main.fragment_episode.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.databinding.FragmentEpisodeBinding
import ru.android.rickandmortymvvm.presentation.CharacterScreenTwo
import ru.android.rickandmortymvvm.presentation.state.EpisodeVS
import ru.android.rickandmortymvvm.presentation.utils.invisible
import ru.android.rickandmortymvvm.presentation.utils.visible

class EpisodeFragment : Fragment(), CharacterNumberAdapter.Listener {

    private val viewModel: EpisodeViewModel by viewModel()

    private val characterAdapter = CharacterNumberAdapter()

    private lateinit var characterListener: CharacterScreenTwo

    private val navArgs by navArgs<EpisodeFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        characterListener =
            FragmentListenerUtils.getFragmentListener(this, CharacterScreenTwo::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentEpisodeBinding>(
            inflater,
            R.layout.fragment_episode,
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
        characterAdapter.setListener(this)
        viewModel.getEpisode(navArgs.id)
        with(recyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = characterAdapter
        }
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.viewEpisodeState.observe(viewLifecycleOwner, {
            when (it) {
                is EpisodeVS.AddEpisode -> {
                    idTextView.text = it.episodesVM.id.toString()
                    nameTextView.text = it.episodesVM.name
                    urlTextView.text = it.episodesVM.url
                    episodeTextView.text = it.episodesVM.episode
                    createdTextView.text = it.episodesVM.created
                    airDateTextView.text = it.episodesVM.airDate
                    it.episodesVM.characters?.let { character -> characterAdapter.add(character) }
                }
                is EpisodeVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visible()
                        linearLayout.invisible()
                    } else {
                        pbPost.invisible()
                        linearLayout.visible()
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is EpisodeVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

    override fun onPostClicked(id: Int) {
        characterListener.openCharacterScreenTwo(id)
    }

}