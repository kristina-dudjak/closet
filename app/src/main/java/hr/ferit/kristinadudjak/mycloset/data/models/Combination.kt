package hr.ferit.kristinadudjak.mycloset.data.models

data class Combination(
    val id: String = "",
    val clothes: List<Clothing> = emptyList()
)

data class CombinationFirestore(
    val id: String = "",
    val clothes: List<String> = emptyList()
)
