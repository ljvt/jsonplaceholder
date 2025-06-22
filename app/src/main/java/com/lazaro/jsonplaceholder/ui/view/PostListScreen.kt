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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lazaro.jsonplaceholder.service.remoto.domain.Post
import com.lazaro.jsonplaceholder.ui.theme.JsonPlaceHolderTheme
import com.lazaro.jsonplaceholder.ui.viewmodel.PostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    onBack: () -> Unit
) {
    val postViewModel = viewModel<PostViewModel>()
    val context = LocalContext.current.applicationContext
    val posts by postViewModel.posts.collectAsState()
    val isLoading by postViewModel.isLoading.collectAsState()
    val error by postViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        postViewModel.fetchPosts(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista de Posts") },
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
                            items(posts) { post ->
                                PostItem(post)
                            }
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${post.id}")
            Text(text = "User ID: ${post.userId}")
            Text(text = "Title: ${post.title}")
            Text(text = "Body: ${post.body}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostListScreenPreview() {
    JsonPlaceHolderTheme {
        PostListScreen(
            onBack = {}
        )
    }
}