package edu.itsco.rickmortyclient.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itsco.rickmortyclient.data.repositorio.RickMortyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import edu.itsco.rickmortyclient.data.api.model.Character
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PersonajesViewModel @Inject constructor(
    private val rickMortyRepository: RickMortyRepository
): ViewModel() {
    private val _state = MutableStateFlow(emptyList<Character>())

    val state: StateFlow<List<Character>>
    get() = _state

    init{
        viewModelScope.launch {
            _state.value = rickMortyRepository.getCharacters().results
        }
    }

}