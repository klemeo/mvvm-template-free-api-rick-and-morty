package ru.android.rickandmortymvvm.presentation.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_episode.view.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.presentation.model.episode.EpisodeResultBody

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    private val mList: MutableList<EpisodeResultBody> = mutableListOf()
    private var mListener: Listener? = null

    fun add(lstResultVM: List<EpisodeResultBody>) {
        mList.addAll(lstResultVM)
        notifyItemInserted(this.itemCount)
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun onPostClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: EpisodeResultBody) {

            itemView.textName.text = result.name

            itemView.cardItem.setOnClickListener {
                mListener?.onPostClicked(result.id ?: 1)
            }

        }
    }
}