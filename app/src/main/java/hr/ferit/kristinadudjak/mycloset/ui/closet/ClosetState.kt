package hr.ferit.kristinadudjak.mycloset.ui.closet

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing

data class ClosetState(
    val clothes: List<Clothing> = emptyList()
)
