package hr.ferit.kristinadudjak.mycloset.data.api.temperature


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("Temperature")
    val temperature: MeasuredTemperature
)