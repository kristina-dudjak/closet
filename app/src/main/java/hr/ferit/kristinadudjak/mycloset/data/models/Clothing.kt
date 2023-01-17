package hr.ferit.kristinadudjak.mycloset.data.models

import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

data class Clothing(
    val id: String = "",
    val image: String = "",
    val colors: List<ClothesColor> = emptyList(),
    val category: ClothesCategory = ClothesCategory.Tops,
    val temperature: List<Temperature> = emptyList()
)
