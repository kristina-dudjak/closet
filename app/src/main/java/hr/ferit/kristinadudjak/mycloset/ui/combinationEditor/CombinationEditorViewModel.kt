package hr.ferit.kristinadudjak.mycloset.ui.combinationEditor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.models.Combination
import hr.ferit.kristinadudjak.mycloset.data.repositories.ClothesRepository
import hr.ferit.kristinadudjak.mycloset.data.repositories.CombinationsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombinationEditorViewModel @Inject constructor(
    private val clothesRepository: ClothesRepository,
    private val combinationsRepository: CombinationsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val combinationId: String = savedStateHandle["combinationId"]!!
    var uiState by mutableStateOf(CombinationEditorState())
        private set

    init {
        viewModelScope.launch {
            uiState =  if (combinationId != "null") {
                val combination = combinationsRepository.getCombination(combinationId)
                combination?.let {
                    CombinationEditorState(
                        id = it.id,
                        clothes = it.clothes
                    )
                } ?: CombinationEditorState()
            } else {
                CombinationEditorState()
            }
        }
    }

    fun addToCombination(clothingId: String) {
        viewModelScope.launch {
            val clothing = clothesRepository.getClothing(clothingId)
            if (clothing != null) {
                uiState = uiState.copy(
                    clothes = uiState.clothes + clothing
                )
            }
        }
    }

    fun onCombinationSave() {
        viewModelScope.launch {
            uiState.run {
                combinationsRepository.saveCombination(
                    Combination(
                        id = id,
                        clothes = clothes
                    )
                )
            }
        }
    }
}