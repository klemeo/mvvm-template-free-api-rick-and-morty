package ru.android.rickandmortymvvm.presentation.locations

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
import kotlinx.android.synthetic.main.fragment_locations.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.base.FragmentListenerUtils
import ru.android.rickandmortymvvm.databinding.FragmentLocationsBinding
import ru.android.rickandmortymvvm.presentation.LocationScreenOne
import ru.android.rickandmortymvvm.presentation.state.LocationsVS
import ru.android.rickandmortymvvm.presentation.utils.gone
import ru.android.rickandmortymvvm.presentation.utils.pageLocations
import ru.android.rickandmortymvvm.presentation.utils.visible

class LocationsFragment : Fragment(), LocationsAdapter.Listener {

    private val viewModel: LocationsViewModel by viewModel()
    private val locationsAdapter = LocationsAdapter()
    private lateinit var locationListener: LocationScreenOne

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationListener =
            FragmentListenerUtils.getFragmentListener(this, LocationScreenOne::class.java)
    }

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
        locationsAdapter.setListener(this)
        viewModel.getLocations()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = locationsAdapter
        }

        nextButton.gone()
        backButton.gone()

        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
        nextButton.setOnClickListener {
            viewModel.getLocations(nextPage)
        }

        backButton.setOnClickListener {
            viewModel.getLocations(prevPage)
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
                    nextPage =
                        if (it.locationsVM.info?.next != null) it.locationsVM.info.next
                            .pageLocations() else null
                    when {
                        nextPage != null -> nextButton.visible()
                        nextPage == null -> nextButton.gone()
                    }
                    prevPage =
                        if (it.locationsVM.info?.prev != null) it.locationsVM.info.prev.toString()
                            .pageLocations() else null
                    when {
                        prevPage != null -> backButton.visible()
                        prevPage == null -> backButton.gone()
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

    override fun onPostClicked(id: Int) {
        locationListener.openLocationScreenOne(id)
    }

}