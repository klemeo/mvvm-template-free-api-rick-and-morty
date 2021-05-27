package ru.android.rickandmortymvvm.presentation.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_location.view.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.presentation.model.location.LocationResultBody

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    private val mList: MutableList<LocationResultBody> = mutableListOf()
    private var mListener: Listener? = null

    fun add(lstResultVM: List<LocationResultBody>) {
        mList.clear()
        mList.addAll(lstResultVM)
        notifyDataSetChanged()
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun onPostClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: LocationResultBody) {

            itemView.textName.text = result.name

            itemView.cardItem.setOnClickListener {
                mListener?.onPostClicked(result.id ?: 1)
            }
        }
    }
}