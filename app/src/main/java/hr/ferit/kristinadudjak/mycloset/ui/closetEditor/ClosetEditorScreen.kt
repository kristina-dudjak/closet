package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    onCategoryClick: (ClothesCategory) -> Unit,
    onTemperatureClick: (Temperature, isSelected: Boolean) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3f)
                .padding(24.dp)
                .border(2.dp, MaterialTheme.colors.onBackground)
                .clickable(onClick = {}),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.empty_image),
                contentDescription = null,
                Modifier.size(40.dp),
                contentScale = ContentScale.Crop,
            )
            Text(stringResource(R.string.add_image))
        }
        Text(stringResource(R.string.colors))
        FlowRow(mainAxisSpacing = 12.dp) {
            for (color in ClothesColor.values()) {
                ClothesColorItem(
                    color = color.color,
                    isSelected = color in state.selectedColors,
                    onClick = { isSelected -> onColorClick(color, isSelected) }
                )
            }
        }
        Text(stringResource(R.string.categories))
        FlowRow(mainAxisSpacing = 12.dp) {
            for (category in ClothesCategory.values()) {
                ClothesCategoryItem(
                    category = category,
                    isSelected = category == state.selectedCategory,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
        Text(stringResource(R.string.temperature))
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
            Text(stringResource(R.string.add_to_closet))
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
                    selectedCategory =
                    ClothesCategory.Bags,
                    selectedTemperatures = listOf(
                        Temperature.Cold
                    )
                ),
                onColorClick = { _, _ -> },
                onCategoryClick = {},
                onTemperatureClick = { _, _ -> }
            )
        }
    }
}