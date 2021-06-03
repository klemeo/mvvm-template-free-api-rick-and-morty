package ru.android.rickandmortymvvm.presentation.location

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
import kotlinx.android.synthetic.main.fragment_location.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.databinding.FragmentLocationBinding
import ru.android.rickandmortymvvm.presentation.LocationScreenTwo
import ru.android.rickandmortymvvm.presentation.state.LocationVS
import ru.android.rickandmortymvvm.presentation.utils.invisible
import ru.android.rickandmortymvvm.presentation.utils.visible

class LocationFragment : Fragment(), LocationAdapter.Listener {

    private val viewModel: LocationViewModel by viewModel()

    private val navArgs by navArgs<LocationFragmentArgs>()

    private val locationAdapter = LocationAdapter()

    private lateinit var locationListener: LocationScreenTwo

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationListener =
            FragmentListenerUtils.getFragmentListener(this, LocationScreenTwo::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentLocationBinding>(
            inflater,
            R.layout.fragment_location,
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
        locationAdapter.setListener(this)
        viewModel.getLocation(navArgs.id)
        with(recyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = locationAdapter
        }
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.viewLocationState.observe(viewLifecycleOwner, {
            when (it) {
                is LocationVS.AddLocation -> {
                    idTextView.text = it.locationsVM.id.toString()
                    nameTextView.text = it.locationsVM.name
                    createdTextView.text = it.locationsVM.created
                    it.locationsVM.residents?.let { characterId -> locationAdapter.add(characterId) }
                }
                is LocationVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visible()
                        linearLayout.invisible()
                    } else {
                        pbPost.invisible()
                        linearLayout.visible()
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is LocationVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

    override fun onPostClicked(id: Int) {
        locationListener.openLocationScreenTwo(id)
    }

}