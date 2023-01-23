package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.kristinadudjak.mycloset.data.ClothesRepository
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetEditorViewModel @Inject constructor(
    private val clothesRepository: ClothesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val clothingId: String = savedStateHandle["clothing"]!!
    var uiState by mutableStateOf(ClosetEditorState())
        private set

    init {
        viewModelScope.launch {
            uiState =  if (clothingId != "null") {
                val clothing = clothesRepository.getClothing(clothingId)
                clothing?.let {
                    ClosetEditorState(
                        id = it.id,
                        selectedImage = it.image,
                        selectedColors = it.colors,
                        selectedCategory = it.category,
                        selectedTemperatures = it.temperature
                    )
                } ?: ClosetEditorState()
            } else {
                ClosetEditorState()
            }
        }

    }

    fun onColorClick(color: ClothesColor, isSelected: Boolean) {
        uiState = uiState.copy(
            selectedColors = uiState.selectedColors.run {
                if (isSelected) plus(color)
                else minus(color)
            }
        )
    }

    fun onCategoryClick(category: ClothesCategory) {
        uiState = uiState.copy(
            selectedCategory = category
        )
    }

    fun onTemperatureClick(temperature: Temperature, isSelected: Boolean) {
        uiState = uiState.copy(
            selectedTemperatures = uiState.selectedTemperatures.run {
                if (isSelected) plus(temperature)
                else minus(temperature)
            }
        )
    }

    fun onImageSelected(imageString: String) {
        uiState = uiState.copy(
            selectedImage = imageString
        )
    }

    fun onClothingSave() {
        viewModelScope.launch {
            uiState.run {
                clothesRepository.saveClothing(
                    Clothing(
                        id = id,
                        image = selectedImage,
                        colors = selectedColors,
                        category = selectedCategory,
                        temperature = selectedTemperatures,
                    )
                )
            }
        }
    }
}