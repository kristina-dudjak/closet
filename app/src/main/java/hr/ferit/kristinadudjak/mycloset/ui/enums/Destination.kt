package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinations.CombinationsScreen
import hr.ferit.kristinadudjak.mycloset.ui.ideas.IdeasScreen

enum class Destination(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
) {
    Closet(
        route = "closet",
        title = R.string.nav_closet,
        icon = Icons.Default.Checkroom,
        screen = { ClosetScreen() }
    ),
    Combinations(
        route = "combinations",
        title = R.string.nav_combinations,
        icon = Icons.Default.Favorite,
        screen = { CombinationsScreen() }
    ),
    Ideas(
        route = "ideas",
        title = R.string.nav_ideas,
        icon = Icons.Default.Lightbulb,
        screen = { IdeasScreen() }
    )
}