package hr.ferit.kristinadudjak.mycloset.data

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor() : ClothesRepository {
    override suspend fun saveClothing(clothing: Clothing) {

    }
}