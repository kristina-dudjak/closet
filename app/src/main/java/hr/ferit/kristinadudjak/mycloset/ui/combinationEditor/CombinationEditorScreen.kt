package hr.ferit.kristinadudjak.mycloset.ui.combinationEditor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetItem

@Composable
fun CombinationEditorScreen(
    pickedClothing: String?,
    onSave: () -> Unit,
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
    onAddClothing: () -> Unit,
    viewModel: CombinationEditorViewModel = hiltViewModel()
) {
    LaunchedEffect(pickedClothing) {
        pickedClothing?.let {
            viewModel.addToCombination(it)
        }
    }

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
        }
    ) { padding ->
        Content(
            state = viewModel.uiState,
            onSave = { viewModel.onCombinationSave(); onSave() },
            onAddClothing = onAddClothing,
            Modifier.padding(padding)
        )
    }
}


@Composable
private fun Content(
    state: CombinationEditorState,
    onSave: () -> Unit,
    onAddClothing: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        for (clothing in state.clothes) {
            ClosetItem(
                clothing,
                onClick = {}
            )
        }
        Button(onClick = onAddClothing) {
            Text(stringResource(R.string.add_clothing))
        }
    }
}