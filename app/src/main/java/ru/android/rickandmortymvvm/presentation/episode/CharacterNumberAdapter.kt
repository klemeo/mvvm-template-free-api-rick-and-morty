package ru.android.rickandmortymvvm.presentation.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_number.view.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.presentation.utils.pageCharacter

class CharacterNumberAdapter: RecyclerView.Adapter<CharacterNumberAdapter.ViewHolder>() {

    private val mList: MutableList<String> = mutableListOf()
    private var mListener: Listener? = null

    fun add(lstResultVM: List<String>) {
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
            .inflate(R.layout.item_number, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: String) {

            itemView.numberView.text = result.pageCharacter().toString()

            itemView.numberCardItem.setOnClickListener {
                mListener?.onPostClicked(result.pageCharacter())
            }
        }
    }
}