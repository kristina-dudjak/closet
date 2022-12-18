package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesCategory
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.enums.Temperature
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetEditorScreen(viewModel: ClosetEditorViewModel = hiltViewModel()) {
    viewModel.uiState?.let {
        Content(
            state = it,
            onColorClick = viewModel::onColorClick,
            onCategoryClick = viewModel::onCategoryClick,
            onTemperatureClick = viewModel::onTemperatureClick
        )
    }
}

@Composable
private fun Content(
    state: ClosetEditorState,
    onColorClick: (ClothesColor, isSelected: Boolean) -> Unit,
    onCategoryClick: (ClothesCategory, isSelected: Boolean) -> Unit,
    onTemperatureClick: (Temperature, isSelected: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text("Colors:")
        FlowRow(mainAxisSpacing = 12.dp) {
            for (color in ClothesColor.values()) {
                ClothesColorItem(
                    color = color.color,
                    isSelected = color in state.selectedColors,
                    onClick = { isSelected -> onColorClick(color, isSelected) }
                )
            }
        }
        Text("Category:")
        FlowRow(mainAxisSpacing = 12.dp) {
            for (category in ClothesCategory.values()) {
                ClothesCategoryItem(
                    category = category,
                    isSelected = category in state.selectedCategories,
                    onClick = { isSelected -> onCategoryClick(category, isSelected) }
                )
            }
        }
        Text("Temperature:")
        FlowRow(mainAxisSpacing = 12.dp) {
            for (temperature in Temperature.values()) {
                ClothesTemperatureItem(
                    temperature = temperature,
                    isSelected = temperature in state.selectedTemperatures,
                    onClick = { isSelected -> onTemperatureClick(temperature, isSelected) }
                )
            }
        }
        Button(onClick = { }) {
            Text("Add to closet")
        }

    }
}

@Preview
@Composable
fun PreviewClosetEditor() {
    MyClosetTheme {
        Surface {
            Content(
                state = ClosetEditorState(
                    selectedColors = listOf(
                        ClothesColor.Blue,
                        ClothesColor.Black
                    ),
                    selectedCategories = listOf(
                        ClothesCategory.Bags,
                        ClothesCategory.Dresses
                    ),
                    selectedTemperatures = listOf(
                        Temperature.Cold
                    )
                ),
                onColorClick = { _, _ -> },
                onCategoryClick = { _, _ -> },
                onTemperatureClick = { _, _ -> }
            )
        }
    }
}