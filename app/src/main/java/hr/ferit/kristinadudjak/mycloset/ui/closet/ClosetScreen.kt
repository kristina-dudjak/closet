package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetScreen(viewModel: ClosetViewModel = hiltViewModel()) {
    Content(
        state = viewModel.uiState
    )
}

@Composable
private fun Content(state: ClosetState) {
    var expandedCategories by remember { mutableStateOf(listOf<ClothesCategory>()) }

    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxHeight()) {
        for (category in state.clothes.keys) {
            item(span = { GridItemSpan(2) }) {
                Row(
                    Modifier
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
            items(state.clothes[category] ?: emptyList()) {
                AnimatedVisibility(category in expandedCategories) {
                    ClosetItem(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCloset() {
    MyClosetTheme {
        Surface {
            Content(state = ClosetState())
        }
    }
}