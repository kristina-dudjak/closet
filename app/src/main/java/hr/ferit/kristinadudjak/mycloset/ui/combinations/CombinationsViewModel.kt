package hr.ferit.kristinadudjak.mycloset.ui.combinations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.repositories.CombinationsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombinationsViewModel @Inject constructor(
    private val combinationsRepository: CombinationsRepository
) : ViewModel() {

    var uiState by mutableStateOf(CombinationsState())
        private set

    init {
        viewModelScope.launch {
            combinationsRepository.getCombinations().collect {
                uiState = CombinationsState(combinations = it)
            }
        }
    }
}