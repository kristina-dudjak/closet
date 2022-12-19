package hr.ferit.kristinadudjak.mycloset.ui.closetEditor


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature

@Composable
fun ClothesTemperatureItem(
    temperature: Temperature,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .then(
                if (isSelected) Modifier.border(
                    4.dp,
                    MaterialTheme.colors.onBackground,
                    CircleShape
                ) else Modifier
            )
            .padding(6.dp)
            .border(
                1.dp,
                MaterialTheme.colors.onBackground,
                CircleShape
            )
            .padding(8.dp)
            .clickable { onClick(!isSelected) }
    ) {
        Text(text = stringResource(temperature.nameRes))
    }
}