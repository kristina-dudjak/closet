package hr.ferit.kristinadudjak.mycloset.data.repositories

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import kotlinx.coroutines.flow.Flow

interface ClothesRepository {

    suspend fun saveClothing(clothing: Clothing)

    suspend fun deleteClothing(clothing: Clothing)

    suspend fun getClothes(): Flow<List<Clothing>>

    suspend fun getClothes(ids: List<String>): Flow<List<Clothing>>

    suspend fun getClothing(id: String): Clothing?
}