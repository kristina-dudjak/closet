package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import hr.ferit.kristinadudjak.mycloset.R

enum class Temperature(
    @StringRes val nameRes: Int,
) {
    Freezing(
        nameRes = R.string.temperature_freezing
    ),
    Cold(
        nameRes = R.string.temperature_cold
    ),
    Mild(
        nameRes = R.string.temperature_mild
    ),
    Warm(
        nameRes = R.string.temperature_warm
    ),
    Hot(
        nameRes = R.string.temperature_hot
    )
}