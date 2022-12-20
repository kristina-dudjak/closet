package hr.ferit.kristinadudjak.mycloset.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.ferit.kristinadudjak.mycloset.data.ClothesRepository
import hr.ferit.kristinadudjak.mycloset.data.ClothesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindClothesRepository(
        clothesRepositoryImpl: ClothesRepositoryImpl
    ): ClothesRepository

}