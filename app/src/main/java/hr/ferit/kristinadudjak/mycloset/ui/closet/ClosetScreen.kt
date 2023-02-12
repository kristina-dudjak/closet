package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory

@Composable
fun ClosetScreen(
    isPickMode: Boolean,
    onGoToClothing: (id: String?) -> Unit,
    onPickClothing: (id: String) -> Unit,
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
    viewModel: ClosetViewModel = hiltViewModel()
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
                    selected = true,
                    onClick = {},
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
                    selected = false,
                    onClick = { onNavigationClick("ideas") },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_ideas)) }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onGoToClothing(null) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Content(
            state = viewModel.uiState,
            isPickMode = isPickMode,
            onGoToClothing = onGoToClothing,
            onPickClothing = onPickClothing,
            Modifier.padding(padding)
        )
    }
}

@Composable
private fun Content(
    state: ClosetState,
    isPickMode: Boolean,
    onGoToClothing: (id: String?) -> Unit,
    onPickClothing: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedCategories by remember { mutableStateOf(listOf<ClothesCategory>()) }

    if (state.clothes.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                stringResource(R.string.empty_closet),
                modifier.padding(4.dp),
                textAlign = TextAlign.Center,
            )
        }
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier.fillMaxHeight()) {
            for (category in state.clothes.keys) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        Modifier
                            .padding(vertical = 16.dp)
                            .clickable {
                                expandedCategories =
                                    if (category in expandedCategories) expandedCategories.minus(
                                        category
                                    )
                                    else expandedCategories.plus(category)
                            }
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(stringResource(category.nameRes))
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
                items(state.clothes[category] ?: emptyList()) { clothing ->
                    AnimatedVisibility(category in expandedCategories) {
                        ClosetItem(
                            clothing,
                            onClick = {
                                if (isPickMode) onPickClothing(clothing.id)
                                else onGoToClothing(clothing.id)
                            },
                            Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}