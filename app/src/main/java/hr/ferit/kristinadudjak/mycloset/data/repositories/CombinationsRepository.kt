package hr.ferit.kristinadudjak.mycloset.data.repositories

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.data.models.Combination
import kotlinx.coroutines.flow.Flow

interface CombinationsRepository {

    suspend fun saveCombination(combination: Combination)
    suspend fun deleteCombination(combination: Combination)
    suspend fun deleteClothingFromCombination(combination: Combination, clothing: Clothing)
    suspend fun getCombinations(): Flow<List<Combination>>
    suspend fun getCombination(id: String): Flow<Combination?>
}