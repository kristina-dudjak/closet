package hr.ferit.kristinadudjak.mycloset.data.repositories

import android.location.Location
import hr.ferit.kristinadudjak.mycloset.data.api.temperature.MeasuredTemperature
import hr.ferit.kristinadudjak.mycloset.data.models.Combination

interface IdeasRepository {

    suspend fun getTemperature(location: Location): MeasuredTemperature

    suspend fun getIdeas(temperature: MeasuredTemperature): List<Combination>
}