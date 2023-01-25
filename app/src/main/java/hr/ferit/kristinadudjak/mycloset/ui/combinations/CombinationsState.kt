package hr.ferit.kristinadudjak.mycloset.ui.combinations

import hr.ferit.kristinadudjak.mycloset.data.models.Combination

data class CombinationsState (
    val combinations: List<Combination> = emptyList()
)