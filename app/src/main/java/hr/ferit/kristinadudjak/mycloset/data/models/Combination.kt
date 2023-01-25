package hr.ferit.kristinadudjak.mycloset.data.models

data class Combination(
    val id: String = "",
    val clothes: List<Clothing> = emptyList()
)
