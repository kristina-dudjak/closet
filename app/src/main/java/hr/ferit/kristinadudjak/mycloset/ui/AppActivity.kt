package hr.ferit.kristinadudjak.mycloset.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.enums.Destination

@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            Content(navController)
        }
    }

    @Composable
    private fun Content(navController: NavHostController) {
        var isShown by remember { mutableStateOf(false) }
        val mContext = LocalContext.current
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
                                Toast.makeText(
                                    mContext,
                                    "Logout",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Text(text = "Logout")
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigation {
                    for (destination in Destination.values()) {
                        with(destination) {
                            BottomNavigationItem(
                                selected = navController.currentDestination?.route == route,
                                onClick = { navController.navigate(route) },
                                icon = { Icon(imageVector = icon, contentDescription = null) },
                                label = { Text(stringResource(title)) }
                            )
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


