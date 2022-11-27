package hr.ferit.kristinadudjak.mycloset.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
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
}


