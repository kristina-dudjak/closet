package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClothesColorItem(
    color: Color,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .then(
                if (isSelected) Modifier.border(
                    4.dp,
                    MaterialTheme.colors.onBackground,
                    CircleShape
                ) else Modifier
            )
            .padding(6.dp)
            .clip(CircleShape)
            .border(
                1.dp,
                MaterialTheme.colors.onBackground,
                CircleShape
            )
            .background(color)
            .clickable { onClick(!isSelected) }
    )
}