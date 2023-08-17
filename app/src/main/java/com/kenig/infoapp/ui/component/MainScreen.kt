package com.kenig.infoapp.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.infoapp.MainViewModel
import com.kenig.infoapp.utils.DrawerEvents
import com.kenig.infoapp.utils.ListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val mainList = mainViewModel.mainList
    val topBarTitle = remember {
        mutableStateOf("Грибы")
    }
    LaunchedEffect(Unit){
        mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(
                title = topBarTitle.value,
                scaffoldState
            ){
                topBarTitle.value = "Избранные"
                mainViewModel.getFavorites()
            }
        },
        drawerContent = {
            DrawerMenu(){ event ->
                when(event){
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainViewModel.getAllItemsByCategory(event.title)
                    }
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(mainList.value){ item ->
                MainListItem(item = item){ listItem ->
                    onClick(listItem)
                }
            }
        }
    }
}


