package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing

@Composable
fun ClosetItem(clothing: Clothing) {
    AsyncImage(clothing.image, contentDescription = null)
}
