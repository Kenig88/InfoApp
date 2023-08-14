package com.kenig.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.kenig.infoapp.ui.component.DrawerMenu
import com.kenig.infoapp.ui.component.Header
import com.kenig.infoapp.ui.component.MainTopBar
import com.kenig.infoapp.ui.theme.InfoAppTheme
import com.kenig.infoapp.ui.utils.DrawerEvents
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState() //с помощью этого открывается Drawer
            val coroutineScope = rememberCoroutineScope()
            val topBarTitle = remember{
                mutableStateOf("Грибы")
            }

            InfoAppTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MainTopBar(
                            title = topBarTitle.value,
                            scaffoldState = scaffoldState
                        )
                    },
                    drawerContent = {
                        DrawerMenu(){ event ->
                            when(event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                }
                            }
                            coroutineScope.launch{
                                scaffoldState.drawerState.close()
                            }
                        }
                    }
                ) {

                }
            }
        }
    }
}



