package com.lazaro.jsonplaceholder.ui.viewmodel

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lazaro.jsonplaceholder.service.remoto.api.RetrofitClient
import com.lazaro.jsonplaceholder.service.remoto.domain.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchPosts(context: Context) {
        if (!isNetworkAvailable(context)) {
            _error.value = "Sem conexão com a internet. Por favor, verifique sua conexão."
            return
        }
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPosts()
                if (response.isSuccessful) {
                    _posts.value = response.body() ?: emptyList()
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                //_error.value = "Network error: ${e.message}"
                when (e) {
                    is java.net.UnknownHostException -> _error.value = "Não foi possível conectar ao servidor. Verifique sua conexão."
                    is java.net.SocketTimeoutException -> _error.value = "Tempo limite da conexão esgotado. Tente novamente."
                    else -> _error.value = "Erro inesperado: ${e.message}"
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}