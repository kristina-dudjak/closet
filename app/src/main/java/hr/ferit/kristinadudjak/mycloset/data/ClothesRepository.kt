package hr.ferit.kristinadudjak.mycloset.data

import hr.ferit.kristinadudjak.mycloset.data.models.Clothing

interface ClothesRepository {

    suspend fun saveClothing(clothing: Clothing)
}