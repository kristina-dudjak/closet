package hr.ferit.kristinadudjak.mycloset.ui.ideas

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.LocationServices
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.combinations.CombinationsItem
import kotlinx.coroutines.tasks.await

@Composable
fun IdeasScreen(
    onNavigationClick: (route: String) -> Unit,
    onLogOutClick: () -> Unit,
    viewModel: IdeasViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var hasLocationPermission by remember { mutableStateOf<Boolean?>(null) }

    val permissionRequestLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasLocationPermission = granted
    }

    LaunchedEffect(true) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionRequestLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            hasLocationPermission = true
        }
    }

    if (hasLocationPermission == true) {
        LaunchedEffect(true) {
            val location = locationClient.lastLocation.await()
            viewModel.updateLocation(location)
        }
    }

    var isDropdownExpanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar {
                Text(stringResource(R.string.app_name), Modifier.weight(1f))
                Box {
                    IconButton(onClick = { isDropdownExpanded = !isDropdownExpanded }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        DropdownMenuItem(onClick = onLogOutClick) {
                            Text(text = "Logout")
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = false,
                    onClick = { onNavigationClick("closet") },
                    icon = { Icon(Icons.Default.Checkroom, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_closet)) }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { onNavigationClick("combinations") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_combinations)) }
                )
                BottomNavigationItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = null) },
                    label = { Text(stringResource(R.string.nav_ideas)) }
                )
            }
        }
    ) { padding ->
        if (hasLocationPermission == true) {
            Content(viewModel.uiState, Modifier.padding(padding))
        } else {
            Text("no permission")
        }
    }
}

@Composable
private fun Content(
    state: IdeasState,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Text(stringResource(R.string.outside_temperature, state.temperature))
        }
        items(state.combinations) { combination ->
            CombinationsItem(
                combination,
                onClick = {}
            )
        }
    }
}