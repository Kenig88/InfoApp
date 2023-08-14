package com.kenig.infoapp.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(title: String, scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(text = title)
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        }
    )
}