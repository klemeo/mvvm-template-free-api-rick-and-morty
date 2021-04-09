package ru.android.rickandmortymvvm.presentation.location

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
import ru.android.rickandmortymvvm.databinding.FragmentLocationsBinding
import ru.android.rickandmortymvvm.presentation.state.LocationsVS

class LocationsFragment : Fragment() {

    private val viewModel: LocationsViewModel by viewModel()
    private val locationsAdapter = LocationsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentLocationsBinding>(
            inflater,
            R.layout.fragment_locations,
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
        viewModel.getLocations()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = locationsAdapter
        }
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewModel.viewLocationsState.observe(viewLifecycleOwner, {
            when (it) {
                is LocationsVS.AddLocations -> {
                    it.locationsVM.results.let { character ->
                        if (character != null) {
                            locationsAdapter.add(character)
                        }
                    }
                }
                is LocationsVS.ShowLoader -> {
                    if (it.showLoader) {
                        pbPost.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    } else {
                        pbPost.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    Log.i("ShowLoader", it.showLoader.toString())
                }
                is LocationsVS.Error -> {
                    it.message?.let { message -> Log.i("Error", message) }
                }
            }
        })
    }

}