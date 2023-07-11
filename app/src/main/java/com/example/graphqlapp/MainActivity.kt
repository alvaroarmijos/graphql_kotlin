package com.example.graphqlapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.graphqlapp.adapter.CharactersListAdapter
import com.example.graphqlapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private val viewModel : CharacterViewModel by viewModels ()

    private val characterListAdapter = CharactersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.characterList.apply {  adapter = characterListAdapter }
        observeData()
        viewModel.queryCharactersList()
    }

    private fun observeData() {
        viewModel.charactersList.observe(this){response ->
            when (response){
                is  ViewState.Success -> {
                    if (response.value?.data?.characters?.results?.size == 0){
                        characterListAdapter.submitList(emptyList())
                        return@observe
                    }
                    val results = response.value?.data?.characters?.results
                    characterListAdapter.submitList(results)
                }
                else -> Unit
            }

        }
    }
}