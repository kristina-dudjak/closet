package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetScreen(viewModel: ClosetViewModel = hiltViewModel()) {
    Content()
}

@Composable
private fun Content() {
    Text("closet")
}

@Preview
@Composable
fun PreviewCloset() {
    MyClosetTheme {
        Surface {
            Content()
        }
    }
}