package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.theme.CustomColors

enum class ClothesColor(
    @StringRes val nameRes: Int,
    val color: Color
) {
    Red(
        nameRes = R.string.color_red,
        color = Color.Red
    ),
    Green(
        nameRes = R.string.color_green,
        color = Color.Green
    ),
    Yellow(
        nameRes = R.string.color_yellow,
        color = Color.Yellow
    ),
    Orange(
        nameRes = R.string.color_orange,
        color = CustomColors.Orange
    ),
    Blue(
        nameRes = R.string.color_blue,
        color = Color.Blue
    ),
    Pink(
        nameRes = R.string.color_pink,
        color = Color.Magenta
    ),
    Purple(
        nameRes = R.string.color_purple,
        color = CustomColors.Purple
    ),
    Black(
        nameRes = R.string.color_black,
        color = Color.Black
    ),
    White(
        nameRes = R.string.color_white,
        color = Color.White
    ),
    Brown(
        nameRes = R.string.color_brown,
        color = CustomColors.Brown
    ),
    Gray(
        nameRes = R.string.color_gray,
        color = Color.Gray
    ),
    Beige(
        nameRes = R.string.color_beige,
        color = CustomColors.Beige
    )
}