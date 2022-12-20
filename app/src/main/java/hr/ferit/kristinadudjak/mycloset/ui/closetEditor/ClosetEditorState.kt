package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

data class ClosetEditorState(
    val selectedImage: String?,
    val selectedColors: List<ClothesColor>,
    val selectedCategory: ClothesCategory,
    val selectedTemperatures: List<Temperature>
)