package hr.ferit.kristinadudjak.mycloset.ui.closet

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory

data class ClosetState(
    val clothes: Map<ClothesCategory, List<Clothing>> = emptyMap()
)
