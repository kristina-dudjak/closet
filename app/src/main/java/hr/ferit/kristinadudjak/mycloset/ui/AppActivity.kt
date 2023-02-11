package hr.ferit.kristinadudjak.mycloset.ui

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.getSystemService
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.kristinadudjak.mycloset.MainActivity
import hr.ferit.kristinadudjak.mycloset.R
import hr.ferit.kristinadudjak.mycloset.ui.closet.ClosetScreen
import hr.ferit.kristinadudjak.mycloset.ui.closetEditor.ClothingEditorScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinationEditor.CombinationEditorScreen
import hr.ferit.kristinadudjak.mycloset.ui.combinations.CombinationsScreen
import hr.ferit.kristinadudjak.mycloset.ui.ideas.IdeasScreen
import hr.ferit.kristinadudjak.mycloset.ui.theme.MyClosetTheme
import java.util.*

@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    private val viewModel: AppActivityViewModel by viewModels()

    private fun setNotifications() {
        val alarmManager = applicationContext.getSystemService<AlarmManager>()

        val channel = NotificationChannel("0", "channel", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "description"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val windowStartMillis = Calendar.getInstance().run {
            timeInMillis = System.currentTimeMillis()
//            if (get(Calendar.HOUR_OF_DAY) > 22) add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 7)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            timeInMillis
        }
        val windowLengthMillis = 60000L
        val onAlarm = AlarmManager.OnAlarmListener {
            val notification = Notification.Builder(
                applicationContext,
                "0"
            )
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My closet")
                .setContentText("Check out your recommended combinations for today!")
                .setAutoCancel(true)
                .build()
            notificationManager.notify(0, notification)
            setNotifications()
        }

        alarmManager!!.setWindow(
            AlarmManager.RTC_WAKEUP,
            windowStartMillis,
            windowLengthMillis,
            "tag",
            onAlarm,
            null
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNotifications()
        setContent {
            val navController = rememberNavController()
            MyClosetTheme {
                var pickedClothing by remember { mutableStateOf<String?>(null) }

                NavHost(
                    navController = navController,
                    startDestination = "closet-graph"
                ) {
                    navigation(
                        startDestination = "closet?isPickMode={isPickMode}",
                        route = "closet-graph"
                    ) {
                        composable(
                            route = "closet?isPickMode={isPickMode}",
                            arguments = listOf(
                                navArgument("isPickMode") {
                                    type = NavType.BoolType
                                    defaultValue = false
                                }
                            )
                        ) { backStackEntry ->
                            ClosetScreen(
                                isPickMode = backStackEntry.arguments?.getBoolean("isPickMode")
                                    ?: false,
                                onGoToClothing = { clothingId ->
                                    navController.navigate("clothingEditor/$clothingId")
                                },
                                onPickClothing = { clothingId ->
                                    pickedClothing = clothingId
                                    navController.navigateUp()
                                },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() }
                            )
                        }
                        composable(
                            route = "clothingEditor/{clothingId}",
                            arguments = listOf(
                                navArgument("clothingId") {}
                            )
                        ) {
                            ClothingEditorScreen(
                                onSave = { navController.navigateUp() },
                                onDelete = { navController.navigateUp() },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() }
                            )
                        }
                    }
                    navigation(
                        startDestination = "combinations",
                        route = "combinations-graph"
                    ) {
                        composable("combinations") {
                            CombinationsScreen(
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() },
                                onGoToCombination = { combinationId ->
                                    navController.navigate("combinationEditor/$combinationId")
                                }
                            )
                        }
                        composable(
                            route = "combinationEditor/{combinationId}",
                            arguments = listOf(
                                navArgument("combinationId") {}
                            )
                        ) {
                            CombinationEditorScreen(
                                pickedClothing = pickedClothing,
                                onPickedClothingConsumed = { pickedClothing = null },
                                onSave = { navController.navigateUp() },
                                onDelete = { navController.navigateUp() },
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() },
                                onAddClothing = { navController.navigate("closet?isPickMode=true") }
                            )
                        }
                    }

                    navigation(startDestination = "ideas", route = "ideas-graph") {
                        composable("ideas") {
                            IdeasScreen(
                                onNavigationClick = { route ->
                                    navController.navigate(route)
                                },
                                onLogOutClick = { logOut() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun logOut() {
        viewModel.logOutUser()
        startActivity(
            Intent(this@AppActivity, MainActivity::class.java)
        )
        finish()
    }
}


