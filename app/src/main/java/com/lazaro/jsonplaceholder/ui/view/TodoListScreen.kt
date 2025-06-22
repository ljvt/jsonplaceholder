package com.lazaro.jsonplaceholder.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lazaro.jsonplaceholder.service.remoto.domain.Todo
import com.lazaro.jsonplaceholder.ui.viewmodel.TodoViewModel


@Composable
fun TodoListScreen(todoViewModel: TodoViewModel = viewModel()) {
    val todos by todoViewModel.todos.collectAsState()
    val isLoading by todoViewModel.isLoading.collectAsState()
    val error by todoViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        todoViewModel.fetchTodos()
    }

    Column(modifier = Modifier.fillMaxSize()) {
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