package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

data class ClosetEditorState(
    val selectedColors: List<ClothesColor>,
    val selectedCategories: List<ClothesCategory>,
    val selectedTemperatures: List<Temperature>
)