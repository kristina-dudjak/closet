package hr.ferit.kristinadudjak.mycloset.ui.enums

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetScreen
import hr.ferit.kristinadudjak.mycloset.ui.closetEditor.ClosetEditorScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinations.CombinationsScreen
import hr.ferit.kristinadudjak.mycloset.ui.ideas.IdeasScreen

enum class Destination(
    val route: String,
    @StringRes val title: Int? = null,
    val icon: ImageVector? = null,
    val args: List<NamedNavArgument> = emptyList(),
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
    ),
    ClosetEditor(
        route = "closetEditor/{clothing}",
        args = listOf(
            navArgument("clothing") {}
        ),
        screen = { ClosetEditorScreen()}
    );

    fun constructRoute(vararg args: Pair<String, Any>): String {
        var result = route
        args.forEach { (name, value) ->
            result = result.replace("{$name}", value.toString())
        }
        return result
    }

    companion object {
        val mainDestinations = listOf(
            Closet, Combinations, Ideas
        )
    }
}

fun NavHostController.navigate(destination: Destination) {
    navigate(destination.route)
}