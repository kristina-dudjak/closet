package hr.ferit.kristinadudjak.mycloset.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.kristinadudjak.mycloset.MainActivity
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetScreen
import hr.ferit.kristinadudjak.mycloset.ui.closetEditor.ClothingEditorScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinations.CombinationsScreen
import hr.ferit.kristinadudjak.mycloset.ui.ideas.IdeasScreen
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    private val viewModel: AppActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MyClosetTheme {
                NavHost(
                    navController = navController,
                    startDestination = "closet-graph"
                ) {
                    navigation(startDestination = "closet", route = "closet-graph") {
                        composable("closet") {
                            ClosetScreen(
                                onGoToClothing = { clothingId ->
                                    navController.navigate("clothingEditor/$clothingId")
                                },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() }
                            )
                        }
                        composable(
                            route = "clothingEditor/{clothingId}",
                            arguments = listOf(
                                navArgument("clothingId") {}
                            )
                        ) {
                            ClothingEditorScreen(
                                onSave = { navController.navigateUp() },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() }
                            )
                        }
                    }

                    navigation(
                        startDestination = "combinations",
                        route = "combinations-graph"
                    ) {
                        composable("combinations") {
                            CombinationsScreen()
                        }
                    }

                    navigation(startDestination = "ideas", route = "ideas-graph") {
                        composable("ideas") {
                            IdeasScreen()
                        }
                    }
                }
            }
        }
    }

    private fun logOut() {
        viewModel.logOutUser()
        startActivity(
            Intent(this@AppActivity, MainActivity::class.java)
        )
        finish()
    }
}


