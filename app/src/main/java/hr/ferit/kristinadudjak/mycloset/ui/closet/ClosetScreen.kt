package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetScreen(viewModel: ClosetViewModel = hiltViewModel()) {
    Content(
        state = viewModel.uiState
    )
}

@Composable
private fun Content(state: ClosetState) {
    LazyColumn(Modifier.fillMaxHeight()) {
        items(state.clothes) { item ->
             Text(text = stringResource(item.category.nameRes))
        }
    }
}

@Preview
@Composable
fun PreviewCloset() {
    MyClosetTheme {
        Surface {
            Content(state = ClosetState(clothes = emptyList()))
        }
    }
}