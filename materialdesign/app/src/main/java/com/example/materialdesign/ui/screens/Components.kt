package com.example.materialdesign.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.icons.filled.Call


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FloatingActionButtonExample() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
                Icon(Icons.Filled.Call, contentDescription = "Add")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Floating Action Button Example")
        }
    }
}

@Composable
@Preview
fun prev3(){
    FloatingActionButtonExample()
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SimpleSnackbar() {
    Scaffold(
    ){
        Snackbar {
            Text(text = "This is a simple Snackbar!")
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FloatingLabelTextFieldExample() {
    Scaffold(
    ){
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label Flutuante") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    }
}

//@Composable
//@Preview
//fun prevtest(){
//    FloatingLabelTextFieldExample()
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationViewExample(navController: NavController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { /* Navigate to Home */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { /* Navigate to Settings */ }
                )
            }
        }
    ) {
        Text("Navigation View Example", modifier = Modifier.padding(16.dp))
    }
}


@Composable
fun TabsExample() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
                listOf("Tab 1", "Tab 2", "Tab 3").forEachIndexed { index, text ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text) }
                    )
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Selected Tab: ${selectedTabIndex + 1}")
            }
        }
    }


}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoordinatorLayoutExample() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Coordinator Layout Example") })
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(50) { index ->
                Text("Item $index", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

//@Composable
//fun CoordinatorLayoutExample() {
//    val scrollState = rememberScrollState()
//
//    Box {
//        // Toolbar fixa no topo
//        TopAppBar(
//            title = { Text("Coordinator Layout Example") },
//            backgroundColor = Color.Blue,
//            contentColor = Color.White
//        )
//
//        // Conteúdo que desliza por baixo da Toolbar
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(scrollState)
//                .padding(top = 56.dp) // Altura da TopAppBar para evitar sobreposição
//        ) {
//            repeat(50) {
//                Text(
//                    text = "Item $it",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//
//                )
//            }
//        }
//
//        // Botão flutuante (Floating Action Button)
//        FloatingActionButton(
//            onClick = { /* Perform action */ },
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        ) {
//            Icon(Icons.Filled.Add, contentDescription = "Add")
//        }
//    }
//}
//
//



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppBarLayoutExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AppBar Example") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) {
        Text("AppBar Content", modifier = Modifier.padding(16.dp))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollapsingToolbarExample() {
    val scrollState = rememberScrollState()

    Scaffold {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    "Collapsing Toolbar",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            repeat(30) {
                Text("Content $it", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun prevComponents(){
    CollapsingToolbarExample()
}










