package com.example.ejercicio1.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio1.R
import com.example.ejercicio1.ui.common.UiState

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private val viewModel: PokemonListViewModel by viewModels()

    private lateinit var adapter: PokemonAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textMessage: TextView
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        textMessage = view.findViewById(R.id.textMessage)
        buttonPrevious = view.findViewById(R.id.buttonPrevious)
        buttonNext = view.findViewById(R.id.buttonNext)

        adapter = PokemonAdapter { pokemon ->
            val bundle = Bundle().apply {
                putInt("id", pokemon.id)
            }
            findNavController().navigate(
                R.id.action_pokemonListFragment_to_pokemonDetailFragment,
                bundle
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        buttonPrevious.setOnClickListener { viewModel.previousPage() }
        buttonNext.setOnClickListener { viewModel.nextPage() }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    textMessage.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    textMessage.visibility = View.GONE
                    adapter.submitList(state.data)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    textMessage.visibility = View.VISIBLE
                    textMessage.text = state.message
                    textMessage.setOnClickListener { viewModel.retry() }
                }
                is UiState.Empty -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    textMessage.visibility = View.VISIBLE
                    textMessage.text = "Sin resultados"
                }
            }
        }
    }
}
