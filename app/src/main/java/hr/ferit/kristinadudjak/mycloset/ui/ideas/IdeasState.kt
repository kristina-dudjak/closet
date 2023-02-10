package hr.ferit.kristinadudjak.mycloset.ui.ideas

import hr.ferit.kristinadudjak.mycloset.data.models.Combination

data class IdeasState(
    val temperature: String = "",
    val combinations: List<Combination> = emptyList()
)