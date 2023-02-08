package hr.ferit.kristinadudjak.mycloset.ui.combinations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hr.ferit.kristinadudjak.mycloset.data.models.Combination

@Composable
fun CombinationsItem(
    combination: Combination,
    onClick: () -> Unit
) {
    Surface(
        Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                combination.clothes.forEach { clothing ->
                    AsyncImage(
                        model = clothing.image,
                        contentDescription = null,
                        Modifier.width(130.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                }
            }
        }
    }
}