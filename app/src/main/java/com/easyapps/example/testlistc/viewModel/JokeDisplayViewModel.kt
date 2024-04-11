package com.easyapps.example.testlistc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyapps.example.testlistc.db.JokeEntity
import com.easyapps.example.testlistc.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeDisplayViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val jokeString = MutableStateFlow(JokeEntity("", ""))

    val jokeFlow: StateFlow<JokeEntity> = jokeString.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.Main) {
            jokeRepository.getJoke().collectLatest {
                jokeString.value = it
            }
        }
    }

    fun markFavorite() {
        viewModelScope.launch {
            jokeRepository.saveFavorite(jokeString.value)
        }
    }


}