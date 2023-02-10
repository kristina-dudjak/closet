package hr.ferit.kristinadudjak.mycloset.data.api.temperature


import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("Value")
    val value: Double
)