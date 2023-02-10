package hr.ferit.kristinadudjak.mycloset.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.ferit.kristinadudjak.mycloset.BuildConfig
import hr.ferit.kristinadudjak.mycloset.data.api.WeatherApi
import hr.ferit.kristinadudjak.mycloset.data.repositories.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    companion object {
        @Provides
        @Singleton
        fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
        ) = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        @Provides
        @Singleton
        fun provideRetrofit(
            client: OkHttpClient
        ) = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): WeatherApi {
            return retrofit.create<WeatherApi>()
        }
    }


    @Binds
    abstract fun bindClothesRepository(
        clothesRepositoryImpl: ClothesRepositoryImpl
    ): ClothesRepository

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindCombinationsRepository(
        combinationsRepositoryImpl: CombinationsRepositoryImpl
    ): CombinationsRepository

    @Binds
    abstract fun bindIdeasRepository(
        ideasRepositoryImpl: IdeasRepositoryImpl
    ): IdeasRepository

}