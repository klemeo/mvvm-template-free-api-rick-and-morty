package ru.android.rickandmortymvvm.presentation.character

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_character.*
import kotlinx.android.synthetic.main.fragment_character.buttonBack
import kotlinx.android.synthetic.main.fragment_character.pbPost
import kotlinx.android.synthetic.main.fragment_character.recyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.databinding.FragmentCharacterBinding
import ru.android.rickandmortymvvm.presentation.EpisodeScreenTwo
import ru.android.rickandmortymvvm.presentation.state.CharacterVS

class CharacterFragment : Fragment(), EpisodeNumberAdapter.Listener {

    private val viewModel: CharacterViewModel by viewModel()

    private val episodeAdapter = EpisodeNumberAdapter()

    private lateinit var episodeListener: EpisodeScreenTwo

    private val navArgs by navArgs<CharacterFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        episodeListener =
            FragmentListenerUtils.getFragmentListener(this, EpisodeScreenTwo::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCharacterBinding>(
            inflater,
            R.layout.fragment_character,
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
        episodeAdapter.setListener(this)
        viewModel.getCharacter(navArgs.id)
        with(recyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = episodeAdapter
        }
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.viewCharacterState.observe(viewLifecycleOwner, {
            when (it) {
                is CharacterVS.AddCharacter -> {

                    context?.let { context ->
                        Glide.with(context)
                            .load(it.charactersVM.image)
                            .into(imagePreview)
                    }

                    textName.text = it.charactersVM.name
                    textStatus.text = it.charactersVM.status
                    textLocation.text = it.charactersVM.origin?.name

                    when (it.charactersVM.status) {
                        "Alive" -> textStatus.setTextColor(Color.parseColor("#4CAF50"))
                        "Dead" -> textStatus.setTextColor(Color.parseColor("#F44336"))
                        else -> textStatus.setTextColor(Color.parseColor("#B89DA8"))
                    }
                    it.charactersVM.episode?.let { episode -> episodeAdapter.add(episode) }
                }
                is CharacterVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visibility = View.VISIBLE
                        cvPostItem.visibility = View.INVISIBLE
                    } else {
                        pbPost.visibility = View.INVISIBLE
                        cvPostItem.visibility = View.VISIBLE
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is CharacterVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

    override fun onPostClicked(id: Int) {
        episodeListener.openEpisodeScreenTwo(id)
    }

}