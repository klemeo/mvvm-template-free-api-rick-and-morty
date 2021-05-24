package ru.android.rickandmortymvvm.presentation.character

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.setImageFitPlaceholderWithGlide
import ru.android.rickandmortymvvm.databinding.FragmentCharacterBinding
import ru.android.rickandmortymvvm.presentation.state.CharacterVS

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModel()

    private val navArgs by navArgs<CharacterFragmentArgs>()

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
        viewModel.getCharacter(navArgs.id)
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.viewCharacterState.observe(viewLifecycleOwner, {
            when (it) {
                is CharacterVS.AddCharacter -> {
                    imagePreview.setImageFitPlaceholderWithGlide(
                        imageUrl = it.charactersVM.image,
                        isRounded = false
                    )
                    textName.text = it.charactersVM.name
                    textStatus.text = it.charactersVM.status
                    textLocation.text = it.charactersVM.origin?.name

                    when (it.charactersVM.status) {
                        "Alive" -> textStatus.setTextColor(Color.parseColor("#4CAF50"))
                        "Dead" -> textStatus.setTextColor(Color.parseColor("#F44336"))
                        else -> textStatus.setTextColor(Color.parseColor("#B89DA8"))
                    }
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

}