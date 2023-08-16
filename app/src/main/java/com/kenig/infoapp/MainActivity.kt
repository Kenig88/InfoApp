package com.kenig.infoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kenig.infoapp.ui.component.InfoScreen
import com.kenig.infoapp.ui.component.MainScreen
import com.kenig.infoapp.ui.theme.InfoAppTheme
import com.kenig.infoapp.utils.ListItem
import com.kenig.infoapp.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null

            InfoAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN
                ) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen { listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }

                    composable(Routes.INFO_SCREEN) {
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}



