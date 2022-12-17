package hr.ferit.kristinadudjak.mycloset.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.kristinadudjak.mycloset.MainActivity
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.enums.Destination
import hr.ferit.kristinadudjak.mycloset.ui.enums.navigate
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MyClosetTheme {
                Content(navController)
            }
        }
    }

    @Composable
    private fun Content(navController: NavHostController) {
        var isShown by remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(stringResource(R.string.app_name), Modifier.weight(1f))
                    Box {
                        IconButton(onClick = { isShown = !isShown }) {
                            Icon(Icons.Default.MoreVert, "")
                        }
                        DropdownMenu(
                            expanded = isShown,
                            onDismissRequest = { isShown = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                AuthUI.getInstance().signOut(this@AppActivity)
                                startActivity(
                                    Intent(this@AppActivity, MainActivity::class.java)
                                )
                                finish()
                            }) {
                                Text(text = "Logout")
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigation {
                    for (destination in Destination.mainDestinations) {
                        with(destination) {
                            BottomNavigationItem(
                                selected = navController.currentDestination?.route == route,
                                onClick = { navController.navigate(route) },
                                icon = { icon?.let { Icon(imageVector = icon, contentDescription = null) } },
                                label = { title?.let { Text(stringResource(it)) } }
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                when (navController.currentBackStackEntryAsState().value?.destination?.route) {
                    Destination.Closet.route -> {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(Destination.ClosetEditor)
                            }
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                    Destination.Combinations.route -> {
                        FloatingActionButton(onClick = {}) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                }
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = Destination.Closet.route,
                Modifier.padding(contentPadding)
            ) {
                for (destination in Destination.values()) {
                    with(destination) {
                        composable(route) { screen() }
                    }
                }
            }
        }
    }
}


