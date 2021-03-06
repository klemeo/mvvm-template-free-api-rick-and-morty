package ru.android.rickandmortymvvm.presentation.characters

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
import kotlinx.android.synthetic.main.fragment_characters.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.databinding.FragmentCharactersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.presentation.CharacterScreenOne
import ru.android.rickandmortymvvm.presentation.state.CharactersVS
import ru.android.rickandmortymvvm.presentation.utils.gone
import ru.android.rickandmortymvvm.presentation.utils.invisible
import ru.android.rickandmortymvvm.presentation.utils.pageCharacters
import ru.android.rickandmortymvvm.presentation.utils.visible

class CharactersFragment : Fragment(), CharactersAdapter.Listener {

    private val viewModel: CharactersViewModel by viewModel()
    private val charactersAdapter = CharactersAdapter()
    private lateinit var characterListener: CharacterScreenOne

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        characterListener =
            FragmentListenerUtils.getFragmentListener(this, CharacterScreenOne::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCharactersBinding>(
            inflater,
            R.layout.fragment_characters,
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
        charactersAdapter.setListener(this)
        viewModel.getCharacters()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = charactersAdapter
        }

        backButton.gone()
        nextButton.gone()

        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }

        nextButton.setOnClickListener {
            viewModel.getCharacters(nextPage)
        }

        backButton.setOnClickListener {
            viewModel.getCharacters(prevPage)
        }

    }

    private fun observeViewModel() {
        viewModel.viewCharactersState.observe(viewLifecycleOwner, {
            when (it) {
                is CharactersVS.AddCharacters -> {
                    it.charactersVM.results.let { character ->
                        if (character != null) {
                            charactersAdapter.add(character)
                        }
                    }
                    nextPage =
                        if (it.charactersVM.info?.next != null) it.charactersVM.info.next
                            .pageCharacters() else null
                    when {
                        nextPage != null -> nextButton.visible()
                        nextPage == null -> nextButton.gone()
                    }
                    prevPage =
                        if (it.charactersVM.info?.prev != null) it.charactersVM.info.prev.toString()
                            .pageCharacters() else null
                    when {
                        prevPage != null -> backButton.visible()
                        prevPage == null -> backButton.gone()
                    }

                }
                is CharactersVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visible()
                        recyclerView.invisible()
                    } else {
                        pbPost.invisible()
                        recyclerView.visible()
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is CharactersVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

    override fun onPostClicked(id: Int) {
        characterListener.openCharacterScreenOne(id)
    }


}