package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetScreen() {
    Text("closet")
}

@Preview
@Composable
fun PreviewCloset() {
    MyClosetTheme {
        ClosetScreen()
    }
}