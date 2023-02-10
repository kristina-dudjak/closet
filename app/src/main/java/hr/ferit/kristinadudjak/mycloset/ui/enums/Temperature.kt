package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import hr.ferit.kristinadudjak.mycloset.R

enum class Temperature(
    @StringRes val nameRes: Int,
    val temperatureRange: IntRange
) {
    Freezing(
        nameRes = R.string.temperature_freezing,
        temperatureRange = -50..0
    ),
    Cold(
        nameRes = R.string.temperature_cold,
        temperatureRange = 1..10
    ),
    Mild(
        nameRes = R.string.temperature_mild,
        temperatureRange = 11..20
    ),
    Warm(
        nameRes = R.string.temperature_warm,
        temperatureRange = 21..30
    ),
    Hot(
        nameRes = R.string.temperature_hot,
        temperatureRange = 31..50
    )
}