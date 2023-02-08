package hr.ferit.kristinadudjak.mycloset.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.kristinadudjak.mycloset.MainActivity
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetScreen
import hr.ferit.kristinadudjak.mycloset.ui.closetEditor.ClothingEditorScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinationEditor.CombinationEditorScreen
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
                var pickedClothing by remember { mutableStateOf<String?>(null) }

                NavHost(
                    navController = navController,
                    startDestination = "closet-graph"
                ) {
                    navigation(startDestination = "closet?isPickMode={isPickMode}", route = "closet-graph") {
                        composable(
                            route = "closet?isPickMode={isPickMode}",
                            arguments = listOf(
                                navArgument("isPickMode") {
                                    type = NavType.BoolType
                                    defaultValue = false
                                }
                            )
                        ) { backStackEntry ->
                            ClosetScreen(
                                isPickMode = backStackEntry.arguments?.getBoolean("isPickMode") ?: false,
                                onGoToClothing = { clothingId ->
                                    navController.navigate("clothingEditor/$clothingId")
                                },
                                onPickClothing = { clothingId ->
                                    pickedClothing = clothingId
                                    navController.navigateUp()
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
                                onDelete = {navController.navigateUp() },
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
                            CombinationsScreen(
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() },
                                onGoToCombination = { combinationId ->
                                    navController.navigate("combinationEditor/$combinationId")
                                }
                            )
                        }
                        composable(
                            route = "combinationEditor/{combinationId}",
                            arguments = listOf(
                                navArgument("combinationId") {}
                            )
                        ) {
                            CombinationEditorScreen(
                                pickedClothing = pickedClothing,
                                onSave = { navController.navigateUp() },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() },
                                onAddClothing = { navController.navigate("closet?isPickMode=true") }
                            )
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


