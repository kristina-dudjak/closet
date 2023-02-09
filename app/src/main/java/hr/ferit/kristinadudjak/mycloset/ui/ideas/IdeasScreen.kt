package hr.ferit.kristinadudjak.mycloset.ui.ideas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.ferit.kristinadudjak.mycloset.R

@Composable
fun IdeasScreen(
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar {
                Text(stringResource(R.string.app_name), Modifier.weight(1f))
                Box {
                    IconButton(onClick = { isDropdownExpanded = !isDropdownExpanded }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        DropdownMenuItem(onClick = onLogOutClick) {
                            Text(text = "Logout")
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = false,
                    onClick = { onNavigationClick("closet") },
                    icon = { Icon(Icons.Default.Checkroom, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_closet)) }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { onNavigationClick("combinations") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_combinations)) }
                )
                BottomNavigationItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_ideas)) }
                )
            }
        }
    ) { padding ->
        Content(
            Modifier.padding(padding)
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier
) {
}