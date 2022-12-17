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
import hr.ferit.kristinadudjak.mycloset.ui.enums.ClothesColor
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme

@Composable
fun ClosetEditorScreen(viewModel: ClosetEditorViewModel = hiltViewModel()) {
    viewModel.uiState?.let {
        Content(it, viewModel::onColorClick)
    }
}

@Composable
private fun Content(
    state: ClosetEditorState,
    onColorClick: (ClothesColor, isSelected: Boolean) -> Unit
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
        Text("Season:")
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
                    )
                ),
                onColorClick = { _, _ -> }
            )
        }
    }
}