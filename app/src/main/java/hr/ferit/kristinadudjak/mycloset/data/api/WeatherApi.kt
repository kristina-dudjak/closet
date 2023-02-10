package hr.ferit.kristinadudjak.mycloset.data.api

import hr.ferit.kristinadudjak.mycloset.BuildConfig
import hr.ferit.kristinadudjak.mycloset.data.api.temperature.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val key = BuildConfig.WEATHER_API_KEY

interface WeatherApi {

    @GET("locations/v1/cities/geoposition/search")
    suspend fun getLocationCode(
        @Query("q") latLng: String,
        @Query("apikey") apiKey: String = key
    ): GeoPosition

    @GET("currentconditions/v1/{locationCode}")
    suspend fun getLocationTemperature(
        @Path("locationCode") locationCode: String,
        @Query("apikey") apiKey: String = key
    ): List<Weather>
}