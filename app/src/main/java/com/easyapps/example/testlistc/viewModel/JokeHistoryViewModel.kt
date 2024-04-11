package com.easyapps.example.testlistc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyapps.example.testlistc.db.JokeEntity
import com.easyapps.example.testlistc.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeHistoryViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _jokeList = MutableStateFlow(listOf<JokeEntity>())
    val jokeList = _jokeList.asStateFlow()

    fun fetch(onlyFav: Boolean = false) {
        viewModelScope.launch {
            if (onlyFav) {
                jokeRepository.getFavJokes().collectLatest {
                    _jokeList.value = it
                }
            } else {
                jokeRepository.getAllJokes().collectLatest {
                    _jokeList.value = it
                }
            }
        }
    }
}