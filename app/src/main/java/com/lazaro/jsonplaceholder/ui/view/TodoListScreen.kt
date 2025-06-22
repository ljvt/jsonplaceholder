package com.lazaro.jsonplaceholder.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lazaro.jsonplaceholder.service.remoto.domain.Todo
import com.lazaro.jsonplaceholder.ui.theme.JsonPlaceHolderTheme
import com.lazaro.jsonplaceholder.ui.viewmodel.TodoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    onBack: () -> Unit
) {
    val todoViewModel = viewModel<TodoViewModel>()
    val todos by todoViewModel.todos.collectAsState()
    val isLoading by todoViewModel.isLoading.collectAsState()
    val error by todoViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        todoViewModel.fetchTodos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista de Todos") },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                content = {
                    if (isLoading) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    } else if (error != null) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Error: $error", color = Color.Red)
                        }
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp)) {
                            items(todos) { todo ->
                                TodoItem(todo)
                            }
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun TodoItem(todo: Todo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${todo.id}")
            Text(text = "User ID: ${todo.userId}")
            Text(text = "Title: ${todo.title}")
            Text(text = "Completed: ${todo.completed}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview() {
    JsonPlaceHolderTheme {
        TodoListScreen(
            onBack = {}
        )
    }
}