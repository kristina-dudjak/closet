package hr.ferit.kristinadudjak.mycloset.data.repositories

import hr.ferit.kristinadudjak.mycloset.data.models.Combination
import kotlinx.coroutines.flow.Flow

interface CombinationsRepository {

    suspend fun saveCombination(combination: Combination)
    suspend fun getCombinations(): Flow<List<Combination>>
    suspend fun getCombination(id: String): Combination?
}