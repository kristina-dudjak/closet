package hr.ferit.kristinadudjak.mycloset.ui.combinations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.R

@Composable
fun CombinationsScreen(
    onGoToCombination: (id: String?) -> Unit,
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
    viewModel: CombinationsViewModel = hiltViewModel()
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
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_combinations)) }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { onNavigationClick("ideas") },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_ideas)) }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onGoToCombination(null) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Content(
            state = viewModel.uiState,
            onGoToCombination = onGoToCombination,
            Modifier.padding(padding)
        )
    }
}

@Composable
private fun Content(
    state: CombinationsState,
    onGoToCombination: (id: String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxHeight()) {
        items(state.combinations) { combination ->
            CombinationsItem(combination, onClick = {
                onGoToCombination(combination.id)
            })

        }
    }
}