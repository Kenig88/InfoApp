package com.kenig.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kenig.infoapp.ui.component.DrawerMenu
import com.kenig.infoapp.ui.component.Header
import com.kenig.infoapp.ui.component.MainTopBar
import com.kenig.infoapp.ui.theme.InfoAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState() //с помощью этого открывается Drawer
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
                        DrawerMenu()
                    }
                ) {

                }
            }
        }
    }
}



