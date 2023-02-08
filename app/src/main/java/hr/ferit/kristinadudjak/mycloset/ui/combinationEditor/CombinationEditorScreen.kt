package hr.ferit.kristinadudjak.mycloset.ui.combinationEditor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetItem

@Composable
fun CombinationEditorScreen(
    pickedClothing: String?,
    onPickedClothingConsumed: () -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
    onAddClothing: () -> Unit,
    viewModel: CombinationEditorViewModel = hiltViewModel()
) {
    LaunchedEffect(pickedClothing) {
        pickedClothing?.let {
            viewModel.addToCombination(it)
            onPickedClothingConsumed()
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
            onDelete = { viewModel.onCombinationDelete(); onDelete() },
            onDeleteFromCombination = { viewModel.onCombinationClothingDelete(it) },
            onAddClothing = onAddClothing,
            Modifier.padding(padding)
        )
    }
}


@Composable
private fun Content(
    state: CombinationEditorState,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    onDeleteFromCombination: (clothing: Clothing) -> Unit,
    onAddClothing: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (clothing in state.clothes) {
            Surface(
                Modifier.padding(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp
            ) {
                Column() {
                    IconButton(
                        onClick = { onDeleteFromCombination(clothing) },
                        Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            "",
                        )
                    }
                    ClosetItem(
                        clothing,
                        onClick = {},
                        Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    )
                }

            }
        }
        IconButton(
            onClick = onAddClothing
        ) {
            Icon(
                Icons.Default.Add,
                "",
            )
        }

        Button(
            onClick = onSave,
            Modifier
                .padding(top = 20.dp)
                .padding(bottom = 16.dp),
            enabled = state.clothes.isNotEmpty()
        ) {
            Text(stringResource(R.string.save_combination))
        }
        if (state.id != "") {
            Button(
                onClick = onDelete,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(stringResource(R.string.remove_from_combinations))
            }
        }

    }


}