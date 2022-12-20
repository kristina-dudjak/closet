package hr.ferit.kristinadudjak.mycloset.ui.closetEditor

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.data.ImageProvider

@Composable
fun AddImageDialog(
    setShowDialog: (Boolean) -> Unit,
    onImageSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val cameraPictureUri = remember { ImageProvider.getImageUri(context) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            onImageSelected(cameraPictureUri.toString())
            setShowDialog(false)
        }
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if(uri != null) {
            onImageSelected(uri.toString())
            setShowDialog(false)
        }
    }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface {
            Column(
                Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { cameraLauncher.launch(cameraPictureUri) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(stringResource(R.string.take_photo))
                }
                Button(
                    onClick = { galleryLauncher.launch("image/*")},
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(stringResource(R.string.from_gallery))
                }
                Button(
                    onClick = { setShowDialog(false) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}