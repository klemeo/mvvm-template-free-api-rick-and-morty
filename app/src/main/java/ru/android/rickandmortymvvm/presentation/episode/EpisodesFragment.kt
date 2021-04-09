package ru.android.rickandmortymvvm.presentation.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.databinding.FragmentEpisodesBinding
import ru.android.rickandmortymvvm.presentation.state.EpisodesVS

class EpisodesFragment : Fragment() {

    private val viewModel: EpisodesViewModel by viewModel()
    private val episodesAdapter = EpisodesAdapter()

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
        viewModel.getEpisodes()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = episodesAdapter
        }
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
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
                }
                is EpisodesVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    } else {
                        pbPost.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is EpisodesVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }


}