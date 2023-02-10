package hr.ferit.kristinadudjak.mycloset.data.repositories

import android.location.Location
import hr.ferit.kristinadudjak.mycloset.data.api.WeatherApi
import hr.ferit.kristinadudjak.mycloset.data.api.temperature.MeasuredTemperature
import hr.ferit.kristinadudjak.mycloset.data.models.Combination
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class IdeasRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val combinationsRepository: CombinationsRepository
) : IdeasRepository {

    override suspend fun getTemperature(location: Location): MeasuredTemperature {
        val geoPosition =
            weatherApi.getLocationCode("${location.latitude},${location.longitude}")
        val weather = weatherApi.getLocationTemperature(geoPosition.key)
        return weather.first().temperature
    }

    override suspend fun getIdeas(temperature: MeasuredTemperature): List<Combination> {
        val temperatureCategory = Temperature.values().find { temperatureCategory ->
            temperature.metric.value.toInt() in temperatureCategory.temperatureRange
        }
        return combinationsRepository.getCombinations().first().filter { combination ->
            combination.clothes.any { temperatureCategory in it.temperature }
        }
    }
}