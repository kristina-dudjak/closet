package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.ClothesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(
    private val clothesRepository: ClothesRepository
) : ViewModel() {

    var uiState by mutableStateOf(ClosetState())
        private set

    init {
        viewModelScope.launch {
            uiState = ClosetState(
                clothes = clothesRepository.getClothes()
            )
        }
    }
}