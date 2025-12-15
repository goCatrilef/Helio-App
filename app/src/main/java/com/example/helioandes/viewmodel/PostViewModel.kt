package com.example.helioandes.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helioandes.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){
     val posts = MutableStateFlow(emptyList<com.example.helioandes.model.Post>())

    fun cargarPosts() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPosts()
            posts.value = response
        }
    }


}