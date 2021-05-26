package ru.android.rickandmortymvvm.presentation.characters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character.view.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.presentation.model.character.CharacterResultBody

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val mList: MutableList<CharacterResultBody> = mutableListOf()
    private var mListener: Listener? = null

    fun add(lstResultVM: List<CharacterResultBody>) {
        mList.clear()
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
            .inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: CharacterResultBody) {

            Glide.with(itemView)
                .load(result.image)
                .into(itemView.imagePreview)

            itemView.textName.text = result.name
            itemView.textStatus.text = result.status
            itemView.textLocation.text = result.origin?.name

            when (result.status) {
                "Alive" -> itemView.textStatus.setTextColor(Color.parseColor("#4CAF50"))
                "Dead" -> itemView.textStatus.setTextColor(Color.parseColor("#F44336"))
                else -> itemView.textStatus.setTextColor(Color.parseColor("#B89DA8"))
            }
            itemView.cvPostItem.setOnClickListener {
                mListener?.onPostClicked(result.id ?: 1)
            }
        }
    }
}