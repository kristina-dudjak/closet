package hr.ferit.kristinadudjak.mycloset.data

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import kotlinx.coroutines.flow.Flow

interface ClothesRepository {

    suspend fun saveClothing(clothing: Clothing)

    suspend fun getClothes() : Flow<Map<ClothesCategory, List<Clothing>>>
    suspend fun getClothing(id: String): Clothing?
}