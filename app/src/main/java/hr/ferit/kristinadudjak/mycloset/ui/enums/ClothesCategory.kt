package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import hr.ferit.kristinadudjak.mycloset.R

enum class ClothesCategory(
    @StringRes val nameRes: Int,
) {
    Tops(
        nameRes = R.string.category_tops
    ),
    Pants(
        nameRes = R.string.category_pants
    ),
    Dresses(
        nameRes = R.string.category_dresses
    ),
    Skirts(
        nameRes = R.string.category_skirts
    ),
    Outerwear(
        nameRes = R.string.category_outerwear
    ),
    Shoes(
        nameRes = R.string.category_shoes
    ),
    Bags(
        nameRes = R.string.category_bags
    ),
    Headwear(
        nameRes = R.string.category_headwear
    ),
    Jewelry(
        nameRes = R.string.category_jewelry
    ),
    Other(
        nameRes = R.string.category_other
    )
}