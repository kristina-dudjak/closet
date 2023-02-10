package hr.ferit.kristinadudjak.mycloset.data.api.temperature


import com.google.gson.annotations.SerializedName

data class MeasuredTemperature(
    @SerializedName("Metric")
    val metric: Metric
) {
    override fun toString() = "${metric.value} ${metric.unit}"
}