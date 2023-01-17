package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.LocalNavController
import hr.ferit.kristinadudjak.mycloset.ui.enums.*
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetEditorScreen(viewModel: ClosetEditorViewModel = hiltViewModel()) {
    viewModel.uiState?.let {
        Content(
            state = it,
            onImageSelected = viewModel::onImageSelected,
            onColorClick = viewModel::onColorClick,
            onCategoryClick = viewModel::onCategoryClick,
            onTemperatureClick = viewModel::onTemperatureClick,
            onSave = viewModel::onClothingSave
        )
    }
}

@Composable
private fun Content(
    state: ClosetEditorState,
    onImageSelected: (String) -> Unit,
    onColorClick: (ClothesColor, isSelected: Boolean) -> Unit,
    onCategoryClick: (ClothesCategory) -> Unit,
    onTemperatureClick: (Temperature, isSelected: Boolean) -> Unit,
    onSave: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val navController = LocalNavController.current
    if (showDialog.value) {
        AddImageDialog(
            setShowDialog = {
                showDialog.value = it
            },
            onImageSelected = onImageSelected
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3f)
                .padding(24.dp)
                .border(2.dp, MaterialTheme.colors.onBackground)
                .clickable(onClick = {
                    showDialog.value = true
                }),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.selectedImage == null) {
                Image(
                    painter = painterResource(R.drawable.empty_image),
                    contentDescription = null,
                    Modifier.size(40.dp),
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.size(12.dp))
                Text(stringResource(R.string.add_image))
            } else {
                AsyncImage(
                    model = state.selectedImage,
                    contentDescription = null,
                    Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
            }
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
        Button(
            onClick = {
                onSave()
                navController.navigateUp()
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(50.dp),
            enabled = state.selectedImage != null
        ) {
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
                    selectedImage = null,
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
                onImageSelected = {},
                onColorClick = { _, _ -> },
                onCategoryClick = {},
                onTemperatureClick = { _, _ -> },
                onSave = {}
            )
        }
    }
}