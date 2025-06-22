package com.lazaro.jsonplaceholder.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lazaro.jsonplaceholder.ui.theme.JsonPlaceHolderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "JSONPlaceholder API") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Button(onClick = { navController.navigate("todos_list") }) {
                        Text("Ver Todos")
                    }
                    Button(
                        onClick = { navController.navigate("posts_list") },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Ver Posts")
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    JsonPlaceHolderTheme {
        MainScreen(navController = rememberNavController())
    }
}