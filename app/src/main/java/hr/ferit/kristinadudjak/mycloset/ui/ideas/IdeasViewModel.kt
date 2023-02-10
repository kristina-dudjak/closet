package hr.ferit.kristinadudjak.mycloset.ui.ideas

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.repositories.IdeasRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor(
    private val ideasRepository: IdeasRepository
) : ViewModel() {

    var uiState by mutableStateOf(IdeasState())
        private set

    fun updateLocation(location: Location) {
        viewModelScope.launch {
            val temperature = ideasRepository.getTemperature(location)
            val ideas = ideasRepository.getIdeas(temperature)
            uiState = uiState.copy(
                temperature = temperature.toString(),
                combinations = ideas
            )
        }
    }

}