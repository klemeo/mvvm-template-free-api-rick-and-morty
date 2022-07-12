package ru.android.rickandmortymvvm.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character.view.*
import ru.android.rickandmortymvvm.R
import ru.android.rickandmortymvvm.presentation.model.character.CharacterResultBody
import ru.android.rickandmortymvvm.presentation.utils.*

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val mList: MutableList<CharacterResultBody> = mutableListOf()
    private var mListener: Listener? = null

    fun add(lstResultVM: List<CharacterResultBody>) {
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
            .inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: CharacterResultBody) {

            with(itemView) {
                Glide.with(this)
                    .load(result.image)
                    .into(imagePreview)

                textName.text = result.name
                textStatus.text = result.status
                textLocation.text = result.location?.name
                textFirstSeen.text = result.origin?.name

                when (result.status) {
                    "Alive" -> textStatus.getColorGreen()
                    "Dead" -> textStatus.getColorRed()
                    else -> textStatus.getColorGrey()
                }
                cvPostItem.setOnClickListener {
                    mListener?.onPostClicked(result.id ?: 1)
                }
            }

        }
    }
}