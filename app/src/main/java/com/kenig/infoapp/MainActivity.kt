package com.kenig.infoapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.kenig.infoapp.ui.component.DrawerMenu
import com.kenig.infoapp.ui.component.MainListItem
import com.kenig.infoapp.ui.component.MainTopBar
import com.kenig.infoapp.ui.theme.InfoAppTheme
import com.kenig.infoapp.utils.DrawerEvents
import com.kenig.infoapp.utils.IdArrayList
import com.kenig.infoapp.utils.ListItem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState() //с помощью этого открывается Drawer
            val coroutineScope = rememberCoroutineScope()
            val mainList = remember {
                mutableStateOf(
                    getListItemsByIndex(
                        0, this
                    )
                )
            }
            val topBarTitle = remember {
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
                        DrawerMenu() { event ->
                            when (event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                    mainList.value = getListItemsByIndex(
                                        event.index,
                                        this@MainActivity
                                    )
                                }
                            }
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(mainList.value) { item ->
                            MainListItem(item = item)
                        }
                    }
                }
            }
        }
    }
}

private fun getListItemsByIndex(index: Int, context: Context): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                itemArray[0],
                itemArray[1]
            )
        )
    }
    return list
}




