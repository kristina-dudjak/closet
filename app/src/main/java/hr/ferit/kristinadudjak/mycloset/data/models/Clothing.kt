package hr.ferit.kristinadudjak.mycloset.data.models

import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

data class Clothing(
    val image: String,
    val colors: List<ClothesColor>,
    val category: ClothesCategory,
    val temperature: List<Temperature>
)
