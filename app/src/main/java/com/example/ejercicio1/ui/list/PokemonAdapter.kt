package com.example.ejercicio1.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio1.R
import com.example.ejercicio1.data.remote.model.PokemonResult

class PokemonAdapter(
    private val onClick: (PokemonResult) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val items = mutableListOf<PokemonResult>()

    fun submitList(list: List<PokemonResult>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.imagePokemon)
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textId: TextView = itemView.findViewById(R.id.textId)

        fun bind(pokemon: PokemonResult) {
            textName.text = pokemon.name.replaceFirstChar { it.uppercase() }
            textId.text = "ID: ${pokemon.id}"

            Glide.with(itemView.context)
                .load(pokemon.imageUrl)
                .into(image)

            itemView.setOnClickListener {
                onClick(pokemon)
            }
        }
    }
}
