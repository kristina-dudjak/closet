package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature
import javax.inject.Inject

@HiltViewModel
class ClosetEditorViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<ClosetEditorState?>(
        ClosetEditorState(
            selectedColors = emptyList(),
            selectedCategories = emptyList(),
            selectedTemperatures = emptyList()
        )
    )

    fun onColorClick(color: ClothesColor, isSelected: Boolean) {
        uiState = uiState?.copy(
            selectedColors = uiState!!.selectedColors.run {
                if (isSelected) plus(color)
                else minus(color)
            }
        )
    }

    fun onCategoryClick(category: ClothesCategory, isSelected: Boolean) {
        uiState = uiState?.copy(
            selectedCategories = uiState!!.selectedCategories.run {
                if (isSelected) plus(category)
                else minus(category)
            }
        )
    }

    fun onTemperatureClick(temperature: Temperature, isSelected: Boolean) {
        uiState = uiState?.copy(
            selectedTemperatures = uiState!!.selectedTemperatures.run {
                if (isSelected) plus(temperature)
                else minus(temperature)
            }
        )
    }
}