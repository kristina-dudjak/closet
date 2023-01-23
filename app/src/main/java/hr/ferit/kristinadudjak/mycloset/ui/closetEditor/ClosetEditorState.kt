package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

data class ClosetEditorState(
    val id: String = "",
    val selectedImage: String = "",
    val selectedColors: List<ClothesColor> = emptyList(),
    val selectedCategory: ClothesCategory = ClothesCategory.Tops,
    val selectedTemperatures: List<Temperature > = emptyList()
)