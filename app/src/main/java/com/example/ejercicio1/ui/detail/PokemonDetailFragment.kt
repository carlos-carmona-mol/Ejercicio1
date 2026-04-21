package com.example.ejercicio1.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ejercicio1.R
import com.example.ejercicio1.ui.common.UiState

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {

    private val viewModel: PokemonDetailViewModel by viewModels()
    private var pokemonId: Int = 0

    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView
    private lateinit var textName: TextView
    private lateinit var textId: TextView
    private lateinit var textTypes: TextView
    private lateinit var textHeight: TextView
    private lateinit var textWeight: TextView
    private lateinit var textError: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        image = view.findViewById(R.id.imagePokemon)
        textName = view.findViewById(R.id.textName)
        textId = view.findViewById(R.id.textId)
        textTypes = view.findViewById(R.id.textTypes)
        textHeight = view.findViewById(R.id.textHeight)
        textWeight = view.findViewById(R.id.textWeight)
        textError = view.findViewById(R.id.textError)

        pokemonId = arguments?.getInt("id") ?: 0
        observeViewModel()
        viewModel.loadPokemon(pokemonId)
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    textError.visibility = View.GONE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    textError.visibility = View.GONE

                    val pokemon = state.data
                    textName.text = pokemon.name.replaceFirstChar { it.uppercase() }
                    textId.text = "ID: ${pokemon.id}"
                    textHeight.text = "Altura: ${pokemon.height}"
                    textWeight.text = "Peso: ${pokemon.weight}"
                    textTypes.text =
                        "Tipos: " + pokemon.types.joinToString { it.type.name }

                    Glide.with(requireContext())
                        .load(pokemon.sprites.frontDefault)
                        .into(image)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    textError.visibility = View.VISIBLE
                    textError.text = state.message
                    textError.setOnClickListener {
                        viewModel.retry(pokemonId)
                    }
                }
                else -> {}
            }
        }
    }
}
