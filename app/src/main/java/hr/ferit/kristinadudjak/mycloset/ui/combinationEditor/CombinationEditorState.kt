package hr.ferit.kristinadudjak.mycloset.ui.combinationEditor

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing

data class CombinationEditorState (
    val id: String = "",
    val clothes: List<Clothing> = emptyList()
)