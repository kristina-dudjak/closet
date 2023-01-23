package hr.ferit.kristinadudjak.mycloset.ui.closet

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import hr.ferit.kristinadudjak.mycloset.data.models.Clothing

@Composable
fun ClosetItem(
    clothing: Clothing,
    onClick: () -> Unit
) {
    AsyncImage(
        clothing.image,
        contentDescription = null,
        Modifier.clickable(onClick = onClick)
    )
}
