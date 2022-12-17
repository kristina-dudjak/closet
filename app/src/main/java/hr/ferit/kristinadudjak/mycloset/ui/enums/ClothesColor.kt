package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import hr.ferit.kristinadudjak.mycloset.R

enum class ClothesColor(
    @StringRes val nameRes: Int? = null,
    val color: Color
) {
    Red(
        nameRes = R.string.color_red,
        color = Color.Red
    ),
    Blue(
        nameRes = R.string.color_blue,
        color = Color.Blue
    ),
    Black(
        nameRes = R.string.color_black,
        color = Color.Black
    ),
    White(
        nameRes = R.string.color_white,
        color = Color.White
    )
}